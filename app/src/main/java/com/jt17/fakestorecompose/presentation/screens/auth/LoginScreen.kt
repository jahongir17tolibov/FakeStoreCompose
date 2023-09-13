package com.jt17.fakestorecompose.presentation.screens.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jt17.fakestorecompose.R
import com.jt17.fakestorecompose.core.base.BaseRoute
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.base.use
import com.jt17.fakestorecompose.core.ui.components.AuthTextFields
import com.jt17.fakestorecompose.core.ui.components.primaryFont
import com.jt17.fakestorecompose.core.ui.components.scaleOnPress
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme
import com.jt17.fakestorecompose.data.remote.dto.LoginUserData
import com.jt17.fakestorecompose.domain.model.Login
import com.jt17.fakestorecompose.presentation.screens.contracts.LoginContract
import com.jt17.fakestorecompose.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateSignUpScreen: () -> Unit,
    onNavigateMainScreen: (login: Login) -> Unit,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        onProvideBaseViewModel(viewModel)
    }

    BaseRoute(baseViewModel = viewModel) {
        LoginScreen(
            loginState = state,
            setLoginUserData = {
                event.invoke(LoginContract.Event.SetLoginUserData(it))
            },
            onNavigateSignUpScreen = {
                onNavigateSignUpScreen()
            },
            onNavigateMainScreen = { login ->
                onNavigateMainScreen(login)
            },
        )
    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun LoginScreen(
    loginState: LoginContract.State,
    setLoginUserData: (loginUserData: LoginUserData) -> Unit,
    onNavigateSignUpScreen: () -> Unit,
    onNavigateMainScreen: (login: Login) -> Unit,
) = FakeStoreComposeTheme {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
//                    .background(color = Color.Green)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                //FOR TYPE USERNAME
                var loginText by remember { mutableStateOf("") }
                AuthTextFields(
                    textValue = loginText,
                    onTextValue = { newText ->
                        loginText = newText
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(fraction = 0.8f)
                        .wrapContentHeight(),
                    labelText = "Username",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Next,
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "username_")
                    },
                )

                //FOR TYPE PASSWORD
                var passwordText by remember { mutableStateOf("") }
                var passwordVisible by remember { mutableStateOf(false) }
                AuthTextFields(
                    textValue = passwordText,
                    onTextValue = { newText ->
                        passwordText = newText
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(fraction = 0.8f)
                        .wrapContentHeight(),
                    labelText = "Password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        autoCorrect = true,
                        imeAction = ImeAction.Next,
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.round_key),
                            contentDescription = "password"
                        )
                    },
                    trailingIcon = {
                        val icon =
                            if (passwordVisible) painterResource(id = R.drawable.round_visibility_24)
                            else painterResource(id = R.drawable.round_visibility_off_24)

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = icon, contentDescription = "password_visibility")
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                //SPACE🗿
                Spacer(modifier = Modifier.height(60.dp))

                //LOGIN BUTTON
                val interactionSource = remember { MutableInteractionSource() }
                TextButton(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.8f)
                        .wrapContentHeight()
                        .scaleOnPress(interactionSource)
                        .indication(
                            indication = null, interactionSource = interactionSource
                        ),
                    interactionSource = interactionSource,
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(5.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                ) {
                    Text(text = "Login", fontFamily = primaryFont(), fontWeight = FontWeight.W300)
                }

                //SPACE🗿
                Spacer(modifier = Modifier.height(10.dp))

                //for navigate to signUp screen
                Text(text = buildAnnotatedString {
                    val color = MaterialTheme.colorScheme.onBackground
                    val signUpTextColor = MaterialTheme.colorScheme.surfaceTint
                    withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
                        withStyle(
                            style = SpanStyle(
                                color = color,
                                fontSize = 12.sp,
                                fontFamily = primaryFont(),
                                fontStyle = FontStyle.Italic
                            )
                        ) {
                            append("Don't have an account? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = signUpTextColor,
                                fontSize = 12.sp,
                                fontFamily = primaryFont(),
                                fontStyle = FontStyle.Italic
                            )
                        ) {
                            append("Sign Up")
                        }

                    }
                }, modifier = Modifier.clickable { onNavigateSignUpScreen() })

            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() = FakeStoreComposeTheme {
    LoginScreen(loginState = LoginContract.State(),
        setLoginUserData = {},
        onNavigateSignUpScreen = {},
        onNavigateMainScreen = {})
}