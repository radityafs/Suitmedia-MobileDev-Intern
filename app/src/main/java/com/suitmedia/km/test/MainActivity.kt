package com.suitmedia.km.test

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suitmedia.km.test.data.remote.UserDataItem
import com.suitmedia.km.test.ui.navigation.Screen
import com.suitmedia.km.test.ui.screen.first.FirstScreen
import com.suitmedia.km.test.ui.screen.second.SecondScreen
import com.suitmedia.km.test.ui.screen.third.ThirdScreen
import com.suitmedia.km.test.ui.theme.SuitmediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitmediaTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(
                        navController = navController, startDestination = Screen.FirstScreen.route
                    ) {
                        composable(Screen.FirstScreen.route) {
                            FirstScreen(navController = navController)
                        }

                        composable(Screen.SecondScreen.route + "/{userName}") { entry ->
                            SecondScreen(
                                navController = navController,
                                userName = entry.arguments?.getString("userName"),
                                userData = entry.savedStateHandle?.get<String>("userData")
                            )
                        }

                        composable(Screen.ThirdScreen.route) {
                            ThirdScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
