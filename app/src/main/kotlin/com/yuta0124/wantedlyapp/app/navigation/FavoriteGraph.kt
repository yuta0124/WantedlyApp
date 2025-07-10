package com.yuta0124.wantedlyapp.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yuta0124.wantedlyapp.feature.favorite.FavoriteScreen
import com.yuta0124.wantedlyapp.feature.favorite.navigation.FavoriteRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.favoriteGraph() {
    navigation<FavoriteGraph>(
        startDestination = FavoriteRoute,
    ) {
        composable<FavoriteRoute> {
            FavoriteScreen()
        }
    }
}

@Serializable
data object FavoriteGraph
