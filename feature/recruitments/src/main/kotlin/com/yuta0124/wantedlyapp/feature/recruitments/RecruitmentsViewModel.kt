package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuta0124.wantedlyapp.core.data.network.response.toRecruitmentList
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import com.yuta0124.wantedlyapp.core.ui.IErrorHandler
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
class RecruitmentsViewModel @Inject constructor(
    private val repository: IWantedlyRepository,
    private val errorHandler: IErrorHandler,
) : ViewModel() {
    private val initialPage = 0
    private var allPageCount = 0

    private val _uiEvents: MutableStateFlow<List<UiEvent>> = MutableStateFlow(emptyList())
    val uiEvents: StateFlow<List<UiEvent>> = _uiEvents.asStateFlow()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = combine(
        _uiState,
        repository.bookmarkCompanies,
    ) { uiState, bookmarkCompanies ->
        uiState.copy(
            recruitments = uiState.recruitments.map { recruitment ->
                val canBookmark = bookmarkCompanies.any { it.id == recruitment.id }
                recruitment.copy(canBookMark = canBookmark)
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState(),
    )

    init {
        fetchRecruitments(keyword = null, page = initialPage, isInitialize = true)
    }

    fun onAction(intent: Intent) {
        when (intent) {
            is Intent.KeywordChange -> {
                _uiState.update { it.copy(keyword = intent.newKeyword) }
            }

            Intent.AdditionalRecruitments -> {
                if (
                    uiState.value.loading == UiState.Loading.ERROR ||
                    uiState.value.loading == UiState.Loading.ADDITIONAL ||
                    uiState.value.isPageLimit
                ) {
                    return
                }

                _uiState.update { it.copy(loading = UiState.Loading.ADDITIONAL) }
                fetchRecruitments(keyword = uiState.value.keyword, page = allPageCount)
            }

            Intent.Search -> {
                allPageCount = 0
                _uiState.update {
                    it.copy(
                        loading = UiState.Loading.INDICATOR,
                        isPageLimit = false,
                    )
                }
                fetchRecruitments(keyword = uiState.value.keyword, page = initialPage)
            }

            is Intent.BookmarkClick -> insertCompanyInBookmarkDatabase(
                id = intent.id,
                canBookmark = intent.canBookmark,
            )
        }
    }

    fun consumeUiEvent(target: UiEvent) {
        _uiEvents.update { e -> e.filterNot { it == target } }
    }

    private fun fetchRecruitments(
        page: Int,
        keyword: String?,
        isInitialize: Boolean = false,
    ) {
        viewModelScope.launch {
            repository.fetchRecruitments(
                keyword = keyword,
                page = page,
            ).fold(
                ifLeft = { error ->
                    if (isInitialize) {
                        _uiState.update { it.copy(loading = UiState.Loading.ERROR) }
                    } else {
                        _uiState.update { it.copy(loading = UiState.Loading.NONE) }
                    }

                    val uiEvent = UiEvent.ShowErrorMessage(errorHandler.onError(error))
                    sendUiEvent(uiEvent)
                },
                ifRight = { response ->
                    if (response.data.isEmpty()) _uiState.update { it.copy(isPageLimit = true) }
                    val newRecruitments = if (uiState.value.loading == UiState.Loading.ADDITIONAL) {
                        uiState.value.recruitments + response.toRecruitmentList()
                    } else {
                        response.toRecruitmentList()
                    }

                    _uiState.update {
                        it.copy(
                            recruitments = newRecruitments,
                            loading = UiState.Loading.NONE,
                        )
                    }
                    allPageCount += response.data.size
                }
            )
        }
    }

    private fun insertCompanyInBookmarkDatabase(id: Int, canBookmark: Boolean) {
        viewModelScope.launch {
            if (canBookmark) repository.insertBookmarkById(id) else repository.deleteBookmarkById(id)
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        _uiEvents.update { it + listOf(event) }
    }
}
