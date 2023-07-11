package com.suitmedia.km.test.ui.screen.second

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.suitmedia.km.test.data.remote.UserDataItem
import com.suitmedia.km.test.ui.component.AppBar
import com.suitmedia.km.test.ui.component.CustomButton
import com.suitmedia.km.test.ui.navigation.Screen
import com.suitmedia.km.test.ui.theme.FontFamilyPoppins
import com.suitmedia.km.test.ui.theme.Typography

@Composable
fun SecondScreen(
    navController: NavController = rememberNavController(),
    userName: String? = "User Name",
    userData: String? = null
) {

    Scaffold(
        topBar = {
            AppBar(title = "Second Screen", onClickBack = {
                navController.popBackStack()
            })
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Welcome !",
                    modifier = Modifier.padding(start = 24.dp, bottom = 5.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamilyPoppins,
                )

                Text(
                    text = userName ?: "John Doe", modifier = Modifier.padding(start = 24.dp),
                    fontFamily = FontFamilyPoppins,
                    style = Typography.h2
                )
            }

            Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                if (userData != null) {
                    val user = Gson().fromJson(userData, UserDataItem::class.java)

                    AsyncImage(
                        model = user.avatar,
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(116.dp)
                            .clip(
                                CircleShape
                            ),
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "${user.firstName ?: "User"} ${user.lastName ?: "User"}",
                        fontSize = 16.sp,
                        fontFamily = FontFamilyPoppins,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = user.email ?: "email@mail.com",
                        fontSize = 12.sp,
                        fontFamily = FontFamilyPoppins,
                        fontWeight = FontWeight.SemiBold,
                    )

                } else {
                    Text(
                        text = "No User Selected",
                        fontFamily = FontFamilyPoppins,
                        style = Typography.h2,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }


            }


            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .align(Alignment.BottomCenter)
            ) {
                CustomButton(onClick = {
                    navController.navigate(Screen.ThirdScreen.route)
                }, text = "Choose a user")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen()
}