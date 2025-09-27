package com.yuta0124.wantedlyapp.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import com.yuta0124.wantedlyapp.core.model.Recruitment
import com.yuta0124.wantedlyapp.core.ui.extensions.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: IWantedlyRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = combine(
        _uiState,
        repository.bookmarkCompanies,
    ) { uiState, bookmarkCompanies ->
        val recruitments = bookmarkCompanies.map { bookmark ->
            Recruitment(
                id = bookmark.id,
                title = bookmark.title,
                companyName = bookmark.companyName,
                canBookMark = bookmark.canBookMark,
                companyLogoImage = bookmark.companyLogoImage,
                thumbnailUrl = bookmark.thumbnailUrl
            )
        }
        val loading = if (recruitments.isEmpty()) UiState.Loading.EMPTY else UiState.Loading.NONE
        uiState.copy(
            loading = loading,
            recruitments = recruitments
        )
    }.stateInWhileSubscribed(UiState())

    fun onAction(intent: Intent) {
        when (intent) {
            is Intent.BookmarkClick -> {
                viewModelScope.launch {
                    repository.deleteBookmarkById(intent.id)
                }
            }
        }
    }
}
