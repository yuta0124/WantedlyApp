package com.yuta0124.wantedlyapp.feature.recruitments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.icons.WantedlyIcons

@Composable
fun SearchBar(
    keyword: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues.Absolute(),
) {
    Box(
        modifier =
            modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = keyword,
            onValueChange = onValueChanged,
            leadingIcon = {
                Icon(
                    imageVector = WantedlyIcons.Search,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.recruitments_keyword_placeholder))
            },
            shape = RoundedCornerShape(50),
            singleLine = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        keyword = "株式会社",
        onValueChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPlaceholderPreview() {
    SearchBar(
        keyword = "",
        onValueChanged = {},
    )
}
