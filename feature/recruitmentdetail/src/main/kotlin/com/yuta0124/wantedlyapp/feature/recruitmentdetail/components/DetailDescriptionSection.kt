package com.yuta0124.wantedlyapp.feature.recruitmentdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.RecruitmentDetailDefaults

@Composable
fun DetailDescriptionSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val textBackgroundColor = MaterialTheme.colorScheme.primary
    val titleTextStyle = MaterialTheme.typography.titleLarge
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = textBackgroundColor,
                        start = Offset(
                            0f,
                            size.height / RecruitmentDetailDefaults.DescriptionTitleBehindStartdiv,
                        ),
                        end = Offset(
                            size.width,
                            size.height / RecruitmentDetailDefaults.DescriptionTitleBehindEnddiv,
                        ),
                        strokeWidth = titleTextStyle.fontSize.value.sp.toPx(),
                    )
                },
            text = title,
            style = titleTextStyle,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailDescriptionSectionPreview() {
    WantedlyAppTheme {
        DetailDescriptionSection(
            modifier = Modifier.fillMaxWidth(),
            title = "タイトルサンプル",
            description = "サンプルの説明文です。ここに詳細な情報が入ります。サンプルの説明文です。サンプルの説明文です。サンプルの説明文です。",
        )
    }
}
