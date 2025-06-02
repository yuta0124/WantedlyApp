package com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDetailRoute(val id: Int)

fun NavController.navigateToDetail(id: Int) = navigate(route = RecruitmentDetailRoute(id))
