package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.yuta0124.wantedlyapp.core.data.network.response.toRecruitmentDetail
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import com.yuta0124.wantedlyapp.core.ui.IErrorHandler
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: IWantedlyRepository,
    private val errorHandler: IErrorHandler,
) : ViewModel() {
    private val _uiEvents: MutableStateFlow<List<UiEvent>> = MutableStateFlow(emptyList())
    val uiEvents: StateFlow<List<UiEvent>> = _uiEvents.asStateFlow()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = combine(
        _uiState,
        repository.bookmarkCompanies,
    ) { uiState, bookmarkCompanies ->
        val canBookmark = bookmarkCompanies.any { it.id == uiState.recruitmentDetail.id }
        uiState.copy(
            recruitmentDetail = uiState.recruitmentDetail.copy(canBookmark = canBookmark)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState(),
    )

    init {
        val recruitmentId = savedStateHandle.toRoute<RecruitmentDetailRoute>().id
        fetchRecruitmentDetail(recruitmentId)
    }

    fun onAction(intent: Intent) {
        when (intent) {
            is Intent.BookmarkClick -> insertCompanyInBookmarkDatabase(intent.canBookmark)
        }
    }

    fun consumeUiEvent(target: UiEvent) {
        _uiEvents.update { e -> e.filterNot { it == target } }
    }

    private fun fetchRecruitmentDetail(recruitmentId: Int) {
        viewModelScope.launch {
            repository.fetchRecruitmentDetail(recruitmentId).fold(
                ifLeft = { error ->
                    _uiState.update { it.copy(loading = UiState.Loading.ERROR) }
                    val uiEvent = UiEvent.ShowErrorMessage(errorHandler.onError(error))
                    sendUiEvent(uiEvent)
                },
                ifRight = { response ->
                    _uiState.update {
                        it.copy(
                            loading = UiState.Loading.NONE,
                            recruitmentDetail = response.toRecruitmentDetail(),
                        )
                    }
                }
            )
        }
    }

    private fun insertCompanyInBookmarkDatabase(canBookmark: Boolean) {
        uiState.value.recruitmentDetail.id?.let { id ->
            viewModelScope.launch {
                if (canBookmark) {
                    repository.insertBookmarkById(id)
                } else {
                    repository.deleteBookmarkById(id)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        _uiEvents.update { it + listOf(event) }
    }
}
