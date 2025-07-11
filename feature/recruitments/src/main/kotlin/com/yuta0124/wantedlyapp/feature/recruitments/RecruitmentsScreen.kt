package com.yuta0124.wantedlyapp.feature.recruitments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.model.Recruitment
import com.yuta0124.wantedlyapp.core.ui.component.CircularIndicator
import com.yuta0124.wantedlyapp.feature.recruitments.components.RecruitmentCard
import com.yuta0124.wantedlyapp.feature.recruitments.components.RecruitmentLoadingCard
import com.yuta0124.wantedlyapp.feature.recruitments.components.SearchBar
import kotlinx.coroutines.launch

@Composable
fun RecruitmentsScreen(
    lazyListState: LazyListState,
    navigateToDetail: (id: Int) -> Unit,
    viewModel: RecruitmentsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents by viewModel.uiEvents.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    uiEvents.forEach { event ->
        when (event) {
            is UiEvent.ShowErrorMessage -> {
                val message = stringResource(event.uiError.messageRes)
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = message,
                        withDismissAction = true,
                    )
                }
                viewModel.consumeUiEvent(event)
            }
        }
    }

    RecruitmentsScreen(
        uiState = uiState,
        lazyListState = lazyListState,
        snackBarHostState = snackBarHostState,
        onAction = viewModel::onAction,
        navigateToDetail = navigateToDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecruitmentsScreen(
    uiState: UiState,
    lazyListState: LazyListState,
    snackBarHostState: SnackbarHostState,
    onAction: (Intent) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var lazyListContentHeight by remember {
        mutableIntStateOf(0)
    }

    val canScroll = remember {
        derivedStateOf {
            val visibleContentMaxHeight =
                lazyListState.layoutInfo.visibleItemsInfo.sumOf { it.size } +
                    lazyListState.layoutInfo.beforeContentPadding +
                    lazyListState.layoutInfo.afterContentPadding

            return@derivedStateOf lazyListContentHeight < visibleContentMaxHeight
        }
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { canScroll.value }
    )

    LaunchedEffect(Unit) {
        snapshotFlow {
            lazyListState.layoutInfo.visibleItemsInfo
        }.collect { visibleItems ->
            if (visibleItems.isNotEmpty()) {
                val lastVisibleItem = visibleItems.last()
                val totalItems = lazyListState.layoutInfo.totalItemsCount

                if (lastVisibleItem.index >= totalItems - 2) {
                    onAction(Intent.AdditionalRecruitments)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.recruitments_title)) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(scrolledContainerColor = MaterialTheme.colorScheme.surface),
            )
        }
    ) { innerPadding ->
        if (uiState.loading == UiState.Loading.INDICATOR) {
            CircularIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
                .onSizeChanged { (_, height) -> lazyListContentHeight = height },
            state = lazyListState,
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            stickyHeader {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    keyword = uiState.keyword ?: "",
                    paddingValues = PaddingValues(vertical = 8.dp),
                    onSearch = {
                        scope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                        onAction(Intent.Search)
                    },
                    onValueChanged = { onAction(Intent.KeywordChange(it)) },
                )
            }

            if (uiState.loading == UiState.Loading.ERROR) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.initialize_error_description),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            } else {
                items(uiState.recruitments, key = { it.id }) { recruitment ->
                    recruitment.run {
                        RecruitmentCard(
                            modifier = Modifier.fillMaxWidth(),
                            thumbnailUrl = thumbnailUrl,
                            title = title,
                            companyName = companyName,
                            companyLogoImage = companyLogoImage,
                            canBookmark = canBookMark,
                            onClick = { navigateToDetail(id) },
                            onBookmarkClick = { canBookmark ->
                                onAction(
                                    Intent.BookmarkClick(
                                        id = id,
                                        canBookmark = canBookmark,
                                    )
                                )
                            },
                        )
                    }
                }

                if (uiState.loading == UiState.Loading.ADDITIONAL) {
                    item {
                        RecruitmentLoadingCard(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecruitmentsScreenPreview() {
    WantedlyAppTheme {
        RecruitmentsScreen(
            uiState = UiState(
                loading = UiState.Loading.NONE,
                recruitments = Recruitment.fake()
            ),
            lazyListState = rememberLazyListState(),
            snackBarHostState = SnackbarHostState(),
            onAction = {},
            navigateToDetail = {},
        )
    }
}
