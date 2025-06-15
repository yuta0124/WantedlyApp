package com.yuta0124.wantedlyapp.feature.recruitments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.ui.component.CompanyInfoHeader
import com.yuta0124.wantedlyapp.core.ui.shimmerBrush
import com.yuta0124.wantedlyapp.feature.recruitments.RecruitmentsDefaults

@Composable
fun RecruitmentLoadingCard(
    modifier: Modifier = Modifier,
) {
    val loadingText = stringResource(R.string.no_data)

    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CompanyInfoHeader(
                companyLogoUrl = loadingText,
                companyName = loadingText,
                canBookmark = false,
                modifier = Modifier.fillMaxWidth(),
                onBookmarkClick = null,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(RecruitmentsDefaults.Ration)
                    .background(shimmerBrush(showShimmer = true)),
            )
            Text(
                text = loadingText,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecruitmentLoadingCardPreview() {
    WantedlyAppTheme {
        RecruitmentLoadingCard(modifier = Modifier.fillMaxWidth())
    }
}
