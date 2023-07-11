package com.suitmedia.km.test.ui.screen.third

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import com.suitmedia.km.test.ui.component.AppBar
import com.suitmedia.km.test.ui.component.CardUser

@Composable
fun ThirdScreen(
    navController: NavController = rememberNavController(),
    viewModel: ThirdViewModel = hiltViewModel()
) {

    val user = viewModel.getUsers().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppBar(
                title = "Third Screen",
                onClickBack = { navController.popBackStack() }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(user.itemCount) { index ->
                    user[index]?.let { dataUser ->
                        CardUser(user = dataUser, onClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "userData",
                                Gson().toJson(dataUser)
                            )
                            navController.popBackStack()
                        })
                    }
                }
            }
            if (user.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}
