package com.suitmedia.km.test.ui.component

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.suitmedia.km.test.R
import com.suitmedia.km.test.ui.theme.FontFamilyPoppins
import androidx.compose.ui.graphics.Color as Colors

@Composable
fun AppBar(
    title: String,
    onClickBack : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Icon Back",
            modifier = Modifier
                .padding(start = 24.dp)
                .size(24.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    onClickBack()
                }
        )

        Text(
            text = title,
            fontSize = 18.sp,
            lineHeight = 27.sp,
            fontFamily = FontFamilyPoppins,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )

        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.BottomStart),
            color = Colors("#E2E3E4".toColorInt())
        )
    }
}

