package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class UiState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class RecruitmentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        val recruitmentId = savedStateHandle.toRoute<RecruitmentDetailRoute>().id
        Log.d("RecruitmentDetailViewModel", "recruitmentId: $recruitmentId")
        // TODO: 募集詳細取得処理
    }
}
