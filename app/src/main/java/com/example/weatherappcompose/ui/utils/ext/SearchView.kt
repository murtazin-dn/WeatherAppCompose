package com.example.weatherappcompose.ui.utils.ext

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.Linear2
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.TertiaryDark
import com.example.weatherappcompose.ui.theme.seProDisplayFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<String>,
    modifier: Modifier = Modifier,
){
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = modifier
            .height(36.dp)
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = true,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = seProDisplayFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.41).sp,
            color = Color.White
        )
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = state.value,
            visualTransformation = VisualTransformation.None,
            innerTextField = innerTextField,
            singleLine = true,
            enabled = true,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(0.dp),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier
                        .size(15.dp),
                    tint = TertiaryDark
                )
            },
            trailingIcon = {
                if (state.value != "") {
                    IconButton(
                        onClick = {
                            state.value = "" // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp),
                            tint = SecondaryDark
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Linear2,
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_city),
                    style = TextStyle(
                        fontFamily = seProDisplayFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.41).sp,
                        color = TertiaryDark
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf("gjfdgdfgdsfg") }
    SearchView(textState)
}