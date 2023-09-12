package com.jt17.fakestorecompose.core.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.scaleOnPress(
    interactionSource: MutableInteractionSource
) = composed {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) 0.9f else 1f, label = "scale_on_animate_value"
    )

    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }

}