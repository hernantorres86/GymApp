@file:OptIn(ExperimentalMaterial3Api::class)

package com.hernan.gymapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorInt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowInsetsControllerCompat
import com.hernan.gymapp.navigation.AppNavigation
import com.hernan.gymapp.ui.theme.GymAppTheme


private var isdarkTheme = true
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setStatusBarColor(getColor(R.color.black))
        setContent {

            GymAppTheme(isdarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.scrim
                ) {
                    //ContainerViewApp()

                    AppNavigation()
                }
            }

        }
    }
}

fun Activity.setStatusBarColor(color: Int) {
    val window = getWindow()
    val decorView: View = window.getDecorView()
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = ColorUtils.calculateLuminance(color) > 0.5
    window.statusBarColor = color
}

@Preview
@Composable
fun MyAppPreview() {
    GymAppTheme(isdarkTheme) {
        AppNavigation()
    }
}

