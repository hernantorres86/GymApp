package com.hernan.gymapp.navigation

sealed class Screens(val routes:String){

    object SplashScreen: Screens("SplashScreen")
    object MainScreen: Screens("MainScreen")
    object TurnsScreen: Screens("TurnsScreen")
    object UsersTypeScreen: Screens("UsersTypeScreen")
    object UsersScreen: Screens("UsersScreen")
}
