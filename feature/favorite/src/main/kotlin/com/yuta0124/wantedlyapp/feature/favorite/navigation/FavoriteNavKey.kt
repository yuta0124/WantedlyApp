package com.yuta0124.wantedlyapp.feature.favorite.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import com.yuta0124.wantedlyapp.feature.favorite.FavoriteScreen
import com.yuta0124.wantedlyapp.feature.favorite.FavoriteViewModel
import kotlinx.serialization.Serializable

@Serializable
data object FavoriteNavKey : NavKey

fun EntryProviderBuilder<NavKey>.favoritesEntry(
    navigateToDetail: (Int) -> Unit,
) {
    entry(FavoriteNavKey) {
        val viewModel = hiltViewModel<FavoriteViewModel>()
        FavoriteScreen(
            viewModel = viewModel,
            navigateToDetail = navigateToDetail,
        )
    }
}
