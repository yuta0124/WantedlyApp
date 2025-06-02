package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuta0124.wantedlyapp.core.data.network.response.toRecruitmentList
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentsViewModel @Inject constructor(
    private val repository: IWantedlyRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val initialPage = 0
    private var allPageCount = 0

    init {
        fetchRecruitments(keyword = null, page = initialPage)
    }

    fun onAction(intent: Intent) {
        when (intent) {
            is Intent.KeywordChange -> {
                _uiState.update { it.copy(keyword = intent.newKeyword) }
            }

            Intent.AdditionalRecruitments -> {
                if (uiState.value.isAdditionalLoading) return
                _uiState.update { it.copy(isAdditionalLoading = true) }
                fetchRecruitments(keyword = uiState.value.keyword, page = allPageCount)
            }

            Intent.Search -> {
                _uiState.update { it.copy(isLoading = true) }
                fetchRecruitments(keyword = uiState.value.keyword, page = initialPage)
            }
        }
    }

    private fun fetchRecruitments(
        keyword: String?,
        page: Int,
    ) {
        viewModelScope.launch {
            repository.fetchRecruitments(
                keyword = keyword,
                page = page,
            ).fold(
                ifLeft = { _ ->
                    // TODO: エラーハンドリング
                },
                ifRight = { response ->
                    val newRecruitments = if (uiState.value.isAdditionalLoading) {
                        uiState.value.recruitments + response.toRecruitmentList()
                    } else {
                        response.toRecruitmentList()
                    }

                    _uiState.update {
                        it.copy(
                            recruitments = newRecruitments,
                            isAdditionalLoading = false,
                            isLoading = false,
                        )
                    }
                    allPageCount += response.data.size
                }
            )
        }
    }
}
