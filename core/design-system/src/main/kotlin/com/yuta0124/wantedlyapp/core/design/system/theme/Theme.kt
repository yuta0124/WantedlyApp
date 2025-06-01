package com.yuta0124.wantedlyapp.core.design.system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = Blue400,
    surface = White,
    background = White,
    surfaceContainerHighest = White,
    outline = Gray500,
)

@Composable
fun WantedlyAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}
