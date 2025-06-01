package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val keyword: String = "",
    val isLoading: Boolean = true,
    val recruitments: List<Recruitment> = emptyList(),
)

@HiltViewModel
class RecruitmentsViewModel @Inject constructor(
    private val repository: IWantedlyRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // TODO: 仮実装
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRecruitments(null, 1).fold(
                ifLeft = { error ->

                },
                ifRight = { response ->

                }
            )

    fun onAction(intent: Intent) {
        when (intent) {
            is Intent.KeywordChange -> {
                _uiState.update { it.copy(keyword = intent.newKeyword) }
            }
        }
    }
}
