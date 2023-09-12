package com.jt17.fakestorecompose.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnnotatedTextView(
    startText: String,
    endText: String,
    textSize: Int = 12,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant
) = Text(text = buildAnnotatedString {
    withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
        withStyle(
            style = SpanStyle(
                color = color,
                fontFamily = primaryFont(),
                fontSize = textSize.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("$startText:  ")
        }
        withStyle(style = SpanStyle(color = color, fontSize = textSize.sp)) {
            append(text = endText)
        }
    }
}, modifier = Modifier.padding(start = 6.dp, end = 6.dp, bottom = 6.dp))