package com.yuta0124.wantedlyapp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailScreen
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsScreen
import kotlinx.serialization.Serializable

@Composable
fun WantedlyNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = RecruitmentsRoute,
    ) {
        composable<RecruitmentsRoute> {
            RecruitmentsScreen(
                navigateToDetail = navHostController::navigateToDetail,
            )
        }
        composable<RecruitmentDetailRoute> {
            RecruitmentDetailScreen()
        }
    }
}

@Serializable
data object RecruitmentsRoute

@Serializable
data class RecruitmentDetailRoute(val id: Int)

fun NavController.navigateToDetail(id: Int) = navigate(route = RecruitmentDetailRoute(id))