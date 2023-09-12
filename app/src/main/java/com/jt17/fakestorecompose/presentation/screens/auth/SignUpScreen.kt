package com.jt17.fakestorecompose.presentation.screens.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SignUpScreen(navController: NavController) = FakeStoreComposeTheme {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column {
            Text(text = "Sign up", modifier = Modifier.align(Alignment.CenterHorizontally))
        }


    }

}