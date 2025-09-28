package com.yuta0124.wantedlyapp.feature.recruitments.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsScreen
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsViewModel
import kotlinx.serialization.Serializable

@Serializable
data object RecruitmentsNavKey : NavKey

fun EntryProviderBuilder<NavKey>.recruitmentsEntry(
    recruitmentsLazyListState: LazyListState,
    navigateToDetail: (Int) -> Unit,
) {
    entry(RecruitmentsNavKey) {
        val viewModel = hiltViewModel<RecruitmentsViewModel>()

        RecruitmentsScreen(
            viewModel = viewModel,
            lazyListState = recruitmentsLazyListState,
            navigateToDetail = navigateToDetail,
        )
    }
}
