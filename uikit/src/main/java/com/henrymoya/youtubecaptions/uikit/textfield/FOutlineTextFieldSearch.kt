package com.henrymoya.youtubecaptions.uikit.textfield

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.henrymoya.youtubecaptions.core.R

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@ExperimentalAnimationApi
@Composable
fun FOutlineTextFieldSearch(
    modifier: Modifier = Modifier,
    label: String = "",
    text: String = "",
    imeAction: ImeAction = ImeAction.Next,
    onSearchAction: (String) -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(start = 0.dp, end = 0.dp, top = 0.dp),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    placeHolderTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var value by remember { mutableStateOf(text) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            value = value,
            maxLines = maxLines,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            label = {
                CompositionLocalProvider(
                    LocalContentColor provides LocalContentColor.current.copy(
                        alpha = 0.74f
                    )
                ) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.secondary,
                        style = placeHolderTextStyle,
                        textAlign = TextAlign.Center
                    )
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = textStyle.fontFamily,
                fontWeight = textStyle.fontWeight,
                fontSize = textStyle.fontSize
            ),
            keyboardOptions = keyboardOptions.copy(
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchAction(value)
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {

                IconButton(onClick = {onSearchAction(value)}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_search),
                        contentDescription = "Search"
                    )
                }
            },
            shape = shape,
            enabled = enabled,
        )
    }
}