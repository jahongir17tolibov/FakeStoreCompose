package com.jt17.fakestorecompose.presentation.screens.bottom_nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun CartScreen(navController: NavHostController) = FakeStoreComposeTheme {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {

        Box {

            Text(
                text = "Cart screen",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 50.sp,
                fontFamily = FontFamily.Cursive
            )

        }

    }

}

@Preview
@Composable
fun CartScreenPreview() {

    CartScreen(navController = rememberNavController())

}