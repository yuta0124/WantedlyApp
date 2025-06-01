package com.yuta0124.wantedlyapp.core.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBrush(
    showShimmer: Boolean = true,
    targetValue: Float = ShimmerBrushDefaults.TargetValue,
): Brush {
    return if (showShimmer) {
        val shimmerColors = ShimmerBrushDefaults.colors

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = ShimmerBrushDefaults.AnimationInitialValue,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(ShimmerBrushDefaults.AnimationTweenMillis),
                repeatMode = RepeatMode.Reverse,
            )
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

object ShimmerBrushDefaults {
    const val TargetValue = 1000f
    const val AnimationInitialValue = 0f
    const val AnimationTweenMillis = 800
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )
}
