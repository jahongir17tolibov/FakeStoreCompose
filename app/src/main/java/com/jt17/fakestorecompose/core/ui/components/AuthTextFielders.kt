package com.jt17.fakestorecompose.core.ui.components

import android.app.Notification.Action
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jt17.fakestorecompose.R
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun AuthTextFields(
    textValue: String,
    onTextValue: (String) -> Unit,
    modifier: Modifier,
    labelText: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)? = null,
) = OutlinedTextField(
    value = textValue,
    onValueChange = onTextValue,
    label = {
        Text(text = labelText, fontFamily = primaryFont(), overflow = TextOverflow.Ellipsis)
    },
    modifier = modifier,
    textStyle = TextStyle(fontFamily = primaryFont(), fontSize = 16.sp),
    singleLine = true,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedTextColor = MaterialTheme.colorScheme.outline,
        unfocusedLabelColor = MaterialTheme.colorScheme.outline,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline,
        errorContainerColor = MaterialTheme.colorScheme.surface,
        errorLabelColor = MaterialTheme.colorScheme.error,
        errorLeadingIconColor = MaterialTheme.colorScheme.error,
        errorTextColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        cursorColor = MaterialTheme.colorScheme.onSurface,
    )
)

@RequiresApi(Build.VERSION_CODES.P)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun AuthTextFieldPreview() = FakeStoreComposeTheme {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        var text by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        AuthTextFields(
            textValue = text,
            onTextValue = { newText ->
                text = newText
            },
            labelText = "Type here...",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                autoCorrect = false,
                imeAction = ImeAction.Next,
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "person_prev")
            },
            trailingIcon = {
                val icon = if (passwordVisible) painterResource(id = R.drawable.round_visibility_24)
                else painterResource(id = R.drawable.round_visibility_off_24)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = icon, contentDescription = "password_visibility")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(fraction = 0.7f)
                .wrapContentHeight(unbounded = true),
        )
    }

}