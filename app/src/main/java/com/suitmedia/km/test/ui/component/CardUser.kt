package com.suitmedia.km.test.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.suitmedia.km.test.data.remote.UserDataItem
import com.suitmedia.km.test.ui.theme.FontFamilyPoppins

@Composable
fun CardUser(
    user: UserDataItem,
    onClick : () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ){
        AsyncImage(
            model = user.avatar,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(49.dp)
                .clip(CircleShape),
            contentDescription = "avatar",
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${user.firstName ?: "User" } ${user.lastName ?: "User"}",
                fontSize = 16.sp,
                fontFamily = FontFamilyPoppins,
                color = Color("#000000".toColorInt()),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = user.email?.toUpperCase() ?: "email@mail.com",
                fontSize = 16.sp,
                fontFamily = FontFamilyPoppins,
                modifier = Modifier.padding(top = 4.dp),
                color = Color("#686777".toColorInt())
            )
        }
    }

    Divider(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        thickness = 1.dp
    )
}


@Preview(showBackground = true)
@Composable
fun CardUserLoading() {
    CardUser(user = UserDataItem(
        id = 0,
        email = "",
        firstName = "",
        lastName = "",
        avatar = "https://reqres.in/img/faces/1-image.jpg"
    ))
}