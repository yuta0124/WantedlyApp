package com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailScreen
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailViewModel
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDetailNavKey(val recruitmentId: Int) : NavKey

fun EntryProviderBuilder<NavKey>.recruitmentDetailEntry(
    popBack: () -> Unit,
) {
    entry<RecruitmentDetailNavKey> { args ->
        val viewModel =
            hiltViewModel<RecruitmentDetailViewModel, RecruitmentDetailViewModel.Factory> { factory ->
                factory.create(args)
            }

        RecruitmentDetailScreen(
            viewModel = viewModel,
            onBackClick = popBack,
        )
    }
}
