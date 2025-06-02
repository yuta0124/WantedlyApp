package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.icons.WantedlyIcons
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.model.RecruitmentDetail
import com.yuta0124.wantedlyapp.core.ui.component.CompanyInfoHeader
import com.yuta0124.wantedlyapp.core.ui.shimmerBrush
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.components.DetailDescriptionSection

@Composable
fun RecruitmentDetailScreen(
    onBackClick: () -> Unit,
    viewModel: RecruitmentDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RecruitmentDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecruitmentDetailScreen(
    uiState: UiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var loadingThumbnail by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val backgroundColor = if (scrollState.value <= 0) {
        MaterialTheme.colorScheme.background.copy(alpha = 0f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = .5f)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { /* no content */ },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = WantedlyIcons.Back,
                            tint = MaterialTheme.colorScheme.surface,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                ),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .background(shimmerBrush(showShimmer = loadingThumbnail)),
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .background(shimmerBrush(showShimmer = loadingThumbnail)),
                    model = uiState.recruitmentDetail.thumbnailUrl,
                    error = painterResource(R.drawable.no_image),
                    contentScale = ContentScale.Crop,
                    onSuccess = { loadingThumbnail = false },
                    onError = { loadingThumbnail = false },
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = innerPadding.calculateBottomPadding())
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                CompanyInfoHeader(
                    modifier = Modifier.fillMaxWidth(),
                    companyLogoUrl = uiState.recruitmentDetail.companyLogoImage,
                    companyName = uiState.recruitmentDetail.companyName
                        ?: stringResource(R.string.loading_text),
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.recruitmentDetail.title,
                    style = MaterialTheme.typography.titleLarge,
                )

                DetailDescriptionSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.what_description_title),
                    description = uiState.recruitmentDetail.whatDescription,
                )

                DetailDescriptionSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.why_description_title),
                    description = uiState.recruitmentDetail.whyDescription,
                )

                DetailDescriptionSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.how_description_title),
                    description = uiState.recruitmentDetail.howDescription,
                )
            }
        }
    }
}

@Preview
@Composable
fun RecruitmentDetailScreenPreview() {
    WantedlyAppTheme {
        RecruitmentDetailScreen(
            uiState = UiState(recruitmentDetail = RecruitmentDetail.fake()),
            onBackClick = {},
        )
    }
}
