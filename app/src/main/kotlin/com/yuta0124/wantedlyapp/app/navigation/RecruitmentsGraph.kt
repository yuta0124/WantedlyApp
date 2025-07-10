package com.yuta0124.wantedlyapp.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.navigateToDetail
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsScreen
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.RecruitmentsRoute
import kotlinx.serialization.Serializable

@Serializable
data object RecruitmentsGraph

fun NavGraphBuilder.recruitmentsGraph(navController: NavController) {
    navigation<RecruitmentsGraph>(
        startDestination = RecruitmentsRoute,
    ) {
        composable<RecruitmentsRoute> {
            RecruitmentsScreen(
                navigateToDetail = navController::navigateToDetail,
            )
        }
    }
}
