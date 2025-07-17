package com.yuta0124.wantedlyapp.app

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuta0124.wantedlyapp.app.navigation.RecruitmentsGraph
import com.yuta0124.wantedlyapp.app.navigation.favoriteGraph
import com.yuta0124.wantedlyapp.app.navigation.recruitmentsGraph
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailScreen
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailRoute

@Composable
fun WantedlyNavHost(
    recruitmentsLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = RecruitmentsGraph,
    ) {
        recruitmentsGraph(
            navController = navHostController,
            recruitmentsLazyListState = recruitmentsLazyListState,
        )
        favoriteGraph(navController = navHostController)
        composable<RecruitmentDetailRoute> {
            RecruitmentDetailScreen(
                onBackClick = navHostController::navigateUp,
            )
        }
    }
}
