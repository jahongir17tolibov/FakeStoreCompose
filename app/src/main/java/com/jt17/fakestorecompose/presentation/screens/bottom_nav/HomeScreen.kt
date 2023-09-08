package com.jt17.fakestorecompose.presentation.screens.bottom_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jt17.fakestorecompose.core.ui.components.primaryFont
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeScreen(navController: NavHostController) = FakeStoreComposeTheme {

    val lazyListState = rememberLazyListState()
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        LazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            state = lazyListState,
            flingBehavior = ScrollableDefaults.flingBehavior()
        ) {

            items(count = 20) {
                Text(
                    text = "Home screen $it",
                    modifier = Modifier.padding(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = primaryFont()
                )
            }


        }


    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun HomeScreenPreview() {

    HomeScreen(navController = rememberNavController())

}

