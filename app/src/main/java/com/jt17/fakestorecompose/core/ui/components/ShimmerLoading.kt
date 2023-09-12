package com.jt17.fakestorecompose.core.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun ShimmerLoading() {

    val shimmerColors = listOf(
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer_effect")

    val transitionAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer_animation",
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = transitionAnim.value, y = transitionAnim.value)
    )

    ShimmerItem(brush = brush)

}

@Composable
fun ShimmerItem(brush: Brush) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(6.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .size(width = 140.dp, 180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush = brush)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .height(20.dp)
                            .fillMaxWidth(fraction = 0.95f)
                            .clip(shape = CircleShape)
                            .background(brush = brush)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .height(20.dp)
                            .fillMaxWidth(fraction = 0.65f)
                            .clip(shape = CircleShape)
                            .background(brush = brush)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(fraction = 0.85f)
                        .clip(shape = CircleShape)
                        .background(brush = brush)
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(fraction = 0.8f)
                        .clip(shape = CircleShape)
                        .background(brush = brush)
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(fraction = 0.7f)
                        .clip(shape = CircleShape)
                        .background(brush = brush)
                )

            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, end = 12.dp, start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
                    .clip(shape = CircleShape)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(32.dp)
                    .clip(shape = CircleShape)
                    .background(brush = brush)
            )

        }

    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun ShimmerItemPrev() = FakeStoreComposeTheme {

    val color = MaterialTheme.colorScheme.outlineVariant

    val shimmerColors = remember {
        mutableStateListOf(
            color.copy(alpha = 0.6f),
            color.copy(alpha = 0.2f),
            color.copy(alpha = 0.5f),
        )
    }

    ShimmerItem(brush = Brush.linearGradient(colors = shimmerColors))
}