package com.yuta0124.wantedlyapp.feature.recruitments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.ui.component.CompanyInfoHeader
import com.yuta0124.wantedlyapp.core.ui.shimmerBrush
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsDefaults

@Composable
fun RecruitmentCard(
    thumbnailUrl: String,
    title: String,
    companyName: String,
    canBookmark: Boolean,
    companyLogoImage: String?,
    onClick: () -> Unit,
    onBookmarkClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var loadingThumbnail by remember { mutableStateOf(true) }

    Card(modifier = modifier, onClick = onClick) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CompanyInfoHeader(
                companyLogoUrl = companyLogoImage,
                companyName = companyName,
                canBookmark = canBookmark,
                onBookmarkClick = onBookmarkClick,
                modifier = Modifier.fillMaxWidth(),
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(RecruitmentsDefaults.Ration)
                    .background(shimmerBrush(showShimmer = loadingThumbnail)),
                model = thumbnailUrl,
                error = painterResource(R.drawable.no_image),
                contentScale = ContentScale.Crop,
                onSuccess = { loadingThumbnail = false },
                onError = { loadingThumbnail = false },
                contentDescription = null,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecruitmentCardPreview() {
    WantedlyAppTheme {
        RecruitmentCard(
            modifier = Modifier.fillMaxWidth(),
            thumbnailUrl = "https://placehold.jp/150x150.png",
            title = "タイトル",
            companyName = "株式会社サンプル",
            canBookmark = true,
            companyLogoImage = "https://placehold.jp/150x150.png",
            onClick = {},
            onBookmarkClick = {},
        )
    }
}
