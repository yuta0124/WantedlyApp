package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme

@Composable
fun RecruitmentsScreen(
    navigateToDetail: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("募集一覧画面")
            Button(
                onClick = {
                    // 仮のIDを使用
                    navigateToDetail(1)
                }
            ) {
                Text("詳細画面へ")
            }
        }
    }
}

@Preview
@Composable
fun RecruitmentsScreenPreview() {
    WantedlyAppTheme {
        RecruitmentsScreen(
            navigateToDetail = {},
        )
    }
}
