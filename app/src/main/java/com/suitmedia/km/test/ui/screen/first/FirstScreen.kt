package com.suitmedia.km.test.ui.screen.first

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.suitmedia.km.test.R
import com.suitmedia.km.test.ui.component.CustomButton
import com.suitmedia.km.test.ui.component.TextInput
import com.suitmedia.km.test.ui.navigation.Screen
import com.suitmedia.km.test.utils.isPalindrome

@Composable
fun FirstScreen(
    navController: NavController = rememberNavController(),
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (palindrome, setPalindrome) = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_photo),
                contentDescription = "Image Profile",
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .size(116.dp)
            )

            TextInput(value = name, onValueChange = setName, placeholder = "Name")
            Spacer(modifier = Modifier.height(15.dp))
            TextInput(value = palindrome, onValueChange = setPalindrome, placeholder = "Palindrome")

            Spacer(modifier = Modifier.height(45.dp))

            CustomButton(onClick = {
                if (name.isEmpty() || palindrome.isEmpty()) {
                    Toast.makeText(
                        navController.context,
                        "Masukan nama dan palindrome terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@CustomButton
                }

                if (isPalindrome(palindrome)) {
                    Toast.makeText(
                        navController.context,
                        "Hello $name, $palindrome is palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        navController.context,
                        "Hello $name, $palindrome is not palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, text = "CHECK")

            Spacer(modifier = Modifier.height(15.dp))

            CustomButton(onClick = {
                if (name.isNotEmpty()) {
                    navController.navigate(Screen.SecondScreen.route + "/$name")
                } else {
                    Toast.makeText(
                        navController.context,
                        "Masukan nama terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, text = "NEXT")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun firstScreenPreview() {
    FirstScreen()
}