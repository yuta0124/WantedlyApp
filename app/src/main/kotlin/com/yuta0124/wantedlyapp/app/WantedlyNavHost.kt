package com.yuta0124.wantedlyapp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailScreen
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.navigateToDetail
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsScreen
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.RecruitmentsRoute

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
            RecruitmentDetailScreen(
                onBackClick = navHostController::navigateUp,
            )
        }
    }
}
