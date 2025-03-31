package com.hernan.gymapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hernan.gymapp.main_screen.presentation.ui.MainScreen
import com.hernan.gymapp.main_screen.presentation.viewmodel.StateViewModel
import com.hernan.gymapp.ui_app.SplashScreen
import com.hernan.gymapp.ui_app.TurnsScreen
import com.hernan.gymapp.ui_app.UsersScreen
import com.hernan.gymapp.ui_app.UsersTypeScreen

@Preview(showBackground = true, widthDp = 320)

@Composable
fun AppNavigation(){
    var isdarkTheme = true


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.routes){

        composable(route = Screens.SplashScreen.routes){
            SplashScreen(navController)
        }

        composable(route = Screens.MainScreen.routes){
            val firstViewModel: StateViewModel = hiltViewModel()
            MainScreen(isdarkTheme, navController, firstViewModel)
        }

        composable(route = Screens.TurnsScreen.routes + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })){
            TurnsScreen(isdarkTheme, navController, it.arguments?.getString("text"))
        }

        composable(route = Screens.UsersTypeScreen.routes + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })){
            UsersTypeScreen(isdarkTheme, navController, it.arguments?.getString("text"))
        }

        composable(route = Screens.UsersScreen.routes + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })){
            UsersScreen(isdarkTheme, navController, it.arguments?.getString("text"))
        }

    }
}