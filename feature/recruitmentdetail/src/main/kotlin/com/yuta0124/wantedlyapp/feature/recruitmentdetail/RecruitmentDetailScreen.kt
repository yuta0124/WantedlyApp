package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme

@Composable
fun RecruitmentDetailScreen(
    viewModel: RecruitmentDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RecruitmentDetailScreen(
        uiState = uiState,
    )
}

@Suppress("UnusedParameter")
@Composable
fun RecruitmentDetailScreen(
    uiState: UiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("募集詳細画面")
        }
    }
}

@Preview
@Composable
fun RecruitmentDetailScreenPreview() {
    WantedlyAppTheme {
        RecruitmentDetailScreen(
            uiState = UiState()
        )
    }
}
