package com.yuta0124.wantedlyapp.core.design.system.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.yuta0124.wantedlyapp.core.design.system.R

object WantedlyIcons {
    val Search = Icons.Rounded.Search
    val Back = Icons.AutoMirrored.Default.ArrowBack
    val BookmarkOn
        @Composable get() = ImageVector.vectorResource(R.drawable.bookmark_on)
    val BookmarkOff
        @Composable get() = ImageVector.vectorResource(R.drawable.bookmark_off)
}
