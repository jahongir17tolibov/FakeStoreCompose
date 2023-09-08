package com.jt17.fakestorecompose.core.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun ErrorView(errorMessage: String) = Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
) {

    Text(
        text = errorMessage,
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 20.dp),
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        fontFamily = primaryFont()
    )

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun ErrorViewPrev() = FakeStoreComposeTheme {
    ErrorView(errorMessage = "Unknown error ")
}