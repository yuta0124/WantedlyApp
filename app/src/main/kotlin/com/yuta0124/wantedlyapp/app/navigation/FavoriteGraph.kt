package com.yuta0124.wantedlyapp.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yuta0124.wantedlyapp.feature.favorite.FavoriteScreen
import com.yuta0124.wantedlyapp.feature.favorite.navigation.FavoriteRoute
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.favoriteGraph(
    navController: NavHostController,
) {
    navigation<FavoriteGraph>(
        startDestination = FavoriteRoute,
    ) {
        composable<FavoriteRoute> {
            FavoriteScreen(
                navigateToDetail = { id ->
                    navController.navigate(RecruitmentDetailRoute(id))
                }
            )
        }
    }
}

@Serializable
data object FavoriteGraph
