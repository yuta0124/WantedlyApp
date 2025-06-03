package com.yuta0124.wantedlyapp.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.core.ui.component.CircularIndicator
import com.yuta0124.wantedlyapp.core.ui.component.CompanyInfoHeader
import com.yuta0124.wantedlyapp.core.ui.shimmerBrush

@Suppress("MagicNumber")
@Composable
fun UiCatalog(modifier: Modifier = Modifier) {
    WantedlyAppTheme {
        Surface(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                CatalogSection("インディケータ") {
                    CircularIndicator(modifier = Modifier.fillMaxWidth())
                }
                CatalogSection("エフェクト") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .background(shimmerBrush(true)),
                    )
                }
                CatalogSection("共通UI要素") {
                    CompanyInfoHeader(
                        modifier = Modifier.fillMaxWidth(),
                        companyLogoUrl = "https://placehold.jp/150x150.png",
                        companyName = "株式会社サンプル",
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogSection(
    sectionTitle: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(sectionTitle, style = MaterialTheme.typography.titleMedium)
        content()
    }
}
