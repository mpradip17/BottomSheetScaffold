package com.example.myapplication

/**
 * created by pradeep
 *
 */

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SmsCodeView(
    modifier: Modifier = Modifier,
    smsCodeLength: Int,
    smsFulled: (String) -> Unit
) {
    val focusRequesters: List<FocusRequester> = remember {
        (0 until smsCodeLength).map { FocusRequester() }
    }
    val enteredNumbers = remember {
        mutableStateListOf(
            *((0 until smsCodeLength).map { "" }.toTypedArray())
        )
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (index in 0 until smsCodeLength) {
            OutlinedTextField(
                modifier = Modifier
                   /* .width(46.dp)
                    .height(60.dp)*/
                    .width(45.dp)
                    .height(55.dp)
                    .focusRequester(focusRequesters[index])
                    .focusRequester(focusRequester = focusRequesters[index])
                    .onKeyEvent { keyEvent: KeyEvent ->
                        val currentValue = enteredNumbers.getOrNull(index) ?: ""
                        if (keyEvent.key == Key.Backspace) {
                            if (currentValue.isNotEmpty()) {
                                enteredNumbers[index] = ""
                                smsFulled.invoke(enteredNumbers.joinToString(separator = ""))
                            } else {
                                focusRequesters.getOrNull(index.minus(1))?.requestFocus()
                            }
                        }
                        false
                    },

                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.blue),
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                ),//textStyle,
                singleLine = true,
                value = enteredNumbers.getOrNull(index)?.trim() ?: "",
                maxLines = 1,
              //  colors = textFieldColors,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledPlaceholderColor=colorResource(R.color.light_blue),
                    placeholderColor=colorResource(R.color.light_blue),
                    cursorColor = colorResource(R.color.blue),
                    focusedBorderColor = colorResource(R.color.blue),
                    backgroundColor = colorResource(R.color.light_blue),
                    unfocusedBorderColor = colorResource(R.color.light_blue)
                ),
                onValueChange = { value: String ->
                    when {
                        value.isDigitsOnly() -> {
                            if (focusRequesters[index].freeFocus()) {
                                when (value.length) {
                                    1 -> {
                                        enteredNumbers[index] = value.trim()
                                        smsFulled.invoke(enteredNumbers.joinToString(separator = ""))
                                        focusRequesters.getOrNull(index + 1)?.requestFocus()
                                    }
                                    2 -> {
                                        focusRequesters.getOrNull(index + 1)?.requestFocus()
                                    }
                                    else -> {
                                        return@OutlinedTextField
                                    }
                                }
                            }
                        }

                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
            )
            val fulled = enteredNumbers.joinToString(separator = "")
            if (fulled.length == smsCodeLength) {
                smsFulled.invoke(fulled)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
