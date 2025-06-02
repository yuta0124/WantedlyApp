package com.yuta0124.wantedlyapp.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.ui.shimmerBrush

@Composable
fun CompanyInfoHeader(
    companyLogoUrl: String?,
    companyName: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var loadingThumbnail by remember { mutableStateOf(false) }

        AsyncImage(
            modifier = Modifier
                .size(24.dp)
                .background(shimmerBrush(showShimmer = loadingThumbnail)),
            model = companyLogoUrl,
            error = painterResource(R.drawable.no_image),
            onSuccess = { loadingThumbnail = false },
            onError = { loadingThumbnail = false },
            contentDescription = null,
        )
        Text(
            text = companyName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompanyInfoHeaderPreview() {
    WantedlyAppTheme {
        CompanyInfoHeader(
            companyLogoUrl = "https://placehold.jp/150x150.png",
            companyName = "株式会社サンプル",
        )
    }
}
