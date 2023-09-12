package com.jt17.fakestorecompose.presentation.screens.bottom_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jt17.fakestorecompose.core.base.BaseRoute
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.base.use
import com.jt17.fakestorecompose.core.ui.components.ProductsListItem
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme
import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.presentation.screens.contracts.HomeContract
import com.jt17.fakestorecompose.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel = koinViewModel(),
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        onProvideBaseViewModel(viewModel)
        event.invoke(HomeContract.Event.OnGetProductsList)
    }

    BaseRoute(baseViewModel = viewModel) {
        HomeScreen(
            productsListState = state,
            onFavouriteClick = {
                event.invoke(HomeContract.Event.OnFavouriteClick(it))
            },
            onLoading = {
                event.invoke(HomeContract.Event.OnLoading)
            },
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeScreen(
    productsListState: HomeContract.State,
    onFavouriteClick: (product: Products) -> Unit,
    onLoading: () -> Unit
) = FakeStoreComposeTheme {

    val lazyListState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        AnimatedVisibility(
            visible = productsListState.onLoading.not(),
            enter = fadeIn(animationSpec = tween(1700, easing = LinearEasing)),
            exit = fadeOut(animationSpec = tween(1700, easing = LinearEasing))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                state = lazyListState
            ) {
                items(items = productsListState.productsList, key = { it.id }) { products ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .animateItemPlacement(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                    ) {
                        val expendedState by rememberSaveable { mutableStateOf(false) }
                        ProductsListItem(
                            products = products,
                            onFavouriteClick = {
                                onFavouriteClick(products)
                            },
                            expendedState = expendedState
                        )
                    }

                }

            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun HomeScreenPreview() {

//    HomeScreen(navController = rememberNavController())

}

