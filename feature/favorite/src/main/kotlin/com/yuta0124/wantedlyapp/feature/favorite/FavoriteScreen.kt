package com.yuta0124.wantedlyapp.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.model.Recruitment
import com.yuta0124.wantedlyapp.core.ui.component.RecruitmentCard

@Composable
internal fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navigateToDetail: (id: Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoriteScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        navigateToDetail = navigateToDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteScreen(
    uiState: UiState,
    onAction: (Intent) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.favorites_title)) },
                colors = TopAppBarDefaults.topAppBarColors(scrolledContainerColor = MaterialTheme.colorScheme.surface),
            )
        }
    ) { innerPadding ->
        when (uiState.loading) {
            UiState.Loading.EMPTY -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.no_favorites),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outline,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            UiState.Loading.NONE -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = innerPadding.calculateTopPadding())
                        .fillMaxSize(),
                    contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(uiState.recruitments, key = { it.id }) { recruitment ->
                        with(recruitment) {
                            RecruitmentCard(
                                modifier = Modifier.fillMaxWidth(),
                                thumbnailUrl = thumbnailUrl,
                                title = title,
                                companyName = companyName,
                                companyLogoImage = companyLogoImage,
                                canBookmark = canBookMark,
                                onClick = { navigateToDetail(id) },
                                onBookmarkClick = { canBookmark ->
                                    onAction(Intent.BookmarkClick(id))
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    WantedlyAppTheme {
        FavoriteScreen(
            uiState = UiState(
                loading = UiState.Loading.NONE,
                recruitments = Recruitment.fake(),
            ),
            onAction = {},
            navigateToDetail = {},
        )
    }
}
