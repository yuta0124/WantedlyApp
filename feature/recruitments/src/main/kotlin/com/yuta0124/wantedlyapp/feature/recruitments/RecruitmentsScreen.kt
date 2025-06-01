package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.ui.component.CircularIndicator
import com.yuta0124.wantedlyapp.feature.recruitments.components.RecruitmentCard
import com.yuta0124.wantedlyapp.feature.recruitments.components.SearchBar

sealed interface Intent {
    data class KeywordChange(val newKeyword: String) : Intent
}

@Composable
fun RecruitmentsScreen(
    navigateToDetail: (id: Int) -> Unit,
    viewModel: RecruitmentsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecruitmentsScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = Modifier,
        navigateToDetail = navigateToDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecruitmentsScreen(
    uiState: UiState,
    onAction: (Intent) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.recruitments_title)) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(scrolledContainerColor = MaterialTheme.colorScheme.surface),
            )
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            CircularIndicator(modifier = Modifier.fillMaxSize())
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            stickyHeader {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    keyword = uiState.keyword,
                    paddingValues = PaddingValues(vertical = 8.dp),
                    onValueChanged = { onAction(Intent.KeywordChange(it)) },
                )
            }

            items(uiState.recruitments, key = { it.id }) { recruitment ->
                RecruitmentCard(
                    modifier = Modifier.fillMaxWidth(),
                    thumbnailUrl = recruitment.thumbnailUrl,
                    title = recruitment.title,
                    companyName = recruitment.companyName,
                    companyLogoImage = recruitment.companyLogoImage,
                )
            }
        }
    }
}

@Preview
@Composable
fun RecruitmentsScreenPreview() {
    WantedlyAppTheme {
        RecruitmentsScreen(
            uiState = UiState(),
            onAction = {},
            navigateToDetail = {},
        )
    }
}
