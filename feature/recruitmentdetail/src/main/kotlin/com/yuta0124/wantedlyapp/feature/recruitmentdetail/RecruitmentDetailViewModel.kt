package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.yuta0124.wantedlyapp.core.data.network.response.toRecruitmentDetail
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: IWantedlyRepository,
) : ViewModel() {
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

    private fun fetchRecruitmentDetail(recruitmentId: Int) {
        viewModelScope.launch {
            repository.fetchRecruitmentDetail(recruitmentId).fold(
                ifLeft = { _ ->
                    // TODO: エラーハンドリング
                },
                ifRight = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
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
}
