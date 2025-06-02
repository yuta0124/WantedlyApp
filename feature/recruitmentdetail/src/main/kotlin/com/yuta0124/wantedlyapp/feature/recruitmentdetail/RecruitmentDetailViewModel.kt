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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: IWantedlyRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        val recruitmentId = savedStateHandle.toRoute<RecruitmentDetailRoute>().id
        fetchRecruitmentDetail(recruitmentId)
    }

    fun fetchRecruitmentDetail(recruitmentId: Int) {
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
}
