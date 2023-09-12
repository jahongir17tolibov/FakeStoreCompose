package com.jt17.fakestorecompose.presentation.activity

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.SetupNavHost
import com.jt17.fakestorecompose.core.ui.components.BottomBar
import com.jt17.fakestorecompose.core.ui.components.bottomBarAnimatedScroll
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FakeStoreComposeTheme {

                var baseViewModel: BaseViewModel by remember { mutableStateOf(BaseViewModel()) }

                val bottomBarHeight = 56.dp

                navController = rememberNavController()
                val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
                val buttonVisible = remember { mutableStateOf(true) }

                LaunchedEffect(bottomBarOffsetHeightPx.floatValue) {
                    buttonVisible.value = bottomBarOffsetHeightPx.floatValue >= -5
                }

                //base of screens
                Scaffold(
                    modifier = Modifier.bottomBarAnimatedScroll(
                        height = bottomBarHeight,
                        offsetHeightPx = bottomBarOffsetHeightPx
                    ),
                    bottomBar = {
                        BottomBar(navController = navController, state = buttonVisible)
                    }
                ) { paddingValues ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        SetupNavHost(
                            navController = navController,
//                            modifier = Modifier.padding(paddingValues.calculateBottomPadding()),
                            onProvideBaseViewModel = { viewModel ->
                                baseViewModel = viewModel
                            }
                        )

                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    FakeStoreComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}