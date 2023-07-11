package com.suitmedia.km.test.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suitmedia.km.test.R

@Composable
fun TextInput(
    modifier: Modifier? = null,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    shape : Shape = RoundedCornerShape(0.dp),
    placeholder: String = "",
    enabled: Boolean = true,
    type: String = "text",
    leadingIcon: @Composable (() -> Unit)? = null,
    isValid: (Boolean) -> Unit = { },
) {

    var textVisible = remember { mutableStateOf(false) }
    val (isValidState, setIsValidState) = remember { mutableStateOf(true) }

    val isFieldValid = when (type) {
        "email" -> value.isNotEmpty() && value.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
        "password" -> value.isNotEmpty() && value.length >= 6
        "text" -> value.isNotEmpty()
        "number" -> value.isNotEmpty() && value.matches(Regex("[0-9]+"))
        "phone" -> value.isNotEmpty() && value.matches(Regex("[0-9]+")) && value.length >= 10
        else -> true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        if (label != null) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                text = label,
            )
        }

        TextField(
            modifier = modifier?.border(
                width = 1.dp,
                color = if (isValidState) Color(15, 13, 35, 51) else Color.Red,
                shape = RoundedCornerShape(10.dp)
            ) ?: Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = if (isValidState) Color(15, 13, 35, 51) else Color.Red,
                    shape = RoundedCornerShape(10.dp)
                ),
            visualTransformation = if (!textVisible.value && type == "password") {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            enabled = enabled,
            value = value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = shape,
            keyboardOptions = when (type) {
                "email" -> KeyboardOptions(keyboardType = KeyboardType.Email)
                "password" -> KeyboardOptions(keyboardType = KeyboardType.Password)
                "text" -> KeyboardOptions(keyboardType = KeyboardType.Text)
                "number" -> KeyboardOptions(keyboardType = KeyboardType.Number)
                else -> KeyboardOptions(keyboardType = KeyboardType.Text)
            },
            leadingIcon = leadingIcon,
            trailingIcon = if (type == "password") {
                {
                    val image = if (textVisible.value) R.drawable.baseline_visibility_24
                    else R.drawable.baseline_visibility_off_24

                    val description = if (textVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = { textVisible.value = !textVisible.value }) {
                        Icon(painterResource(id = image), contentDescription = description)
                    }
                }
            } else null,
            onValueChange = {
                onValueChange(it)
                setIsValidState(isFieldValid)
                isValid(isFieldValid)
            },
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(), text = placeholder
                    )
                }
            },
            singleLine = true,
        )
    }

}