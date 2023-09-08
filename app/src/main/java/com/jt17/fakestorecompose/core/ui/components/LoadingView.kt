package com.jt17.fakestorecompose.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(34.dp)
                .width(34.dp)
                .align(Alignment.Center)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoadingViewPrev() = FakeStoreComposeTheme {
    Surface(color = MaterialTheme.colorScheme.background) {
        LoadingView()
    }

}