@file:OptIn(ExperimentalMaterial3Api::class)

package com.hernan.gymapp.ui_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hernan.gymapp.R
import com.hernan.gymapp.navigation.Screens

private lateinit var navControl: NavHostController

@Composable
fun MainScreen(isdarkTheme: Boolean, navController: NavHostController) {
    navControl = navController

    LazyColumn(horizontalAlignment =Alignment.CenterHorizontally
        ,modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(color = MaterialTheme.colorScheme.scrim)) {

        item { MainToolbar() }
        //if (isdarkTheme) item {  } else item { BackImage(R.drawable.logo_blanco) }
        item { BackImage(R.drawable.logo_eze_solo_nombre) }
        item { OptionsApp(MaterialTheme.colorScheme.surfaceTint, stringResource(id = R.string.turns)) }
        item { OptionsApp(MaterialTheme.colorScheme.surfaceTint, stringResource(id = R.string.members)) }
        item { OptionsApp(MaterialTheme.colorScheme.surfaceTint, stringResource(id = R.string.client_types) ) }
        /*item { Row() {
            OptionsApp(MaterialTheme.colorScheme.onSecondary, R.string.turns)
            OptionsApp(MaterialTheme.colorScheme.onSecondary, R.string.members)
            OptionsApp(MaterialTheme.colorScheme.onSecondary, R.string.members)
        } }*/


    }
}

@Composable
fun BackImage(image: Int) {
    Image(
        painter = painterResource(image),  //R.drawable.ab1_inversions
        contentDescription = "Imagen Principal",
        contentScale = ContentScale.Crop, // puedo dar 3 estilos de imagen. Fit, FillBounds y Crop
        modifier = Modifier
            .fillMaxHeight()
            .width(260.dp)
            .clip(RectangleShape)
            .padding(vertical = 12.dp)

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolbar() {

    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.white)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.scrim)
    )
}

@Composable
fun OptionsApp(backColor: Color, text: String) {

    Card(
        modifier = Modifier
            .height(86.dp)
            .padding(vertical = 10.dp, horizontal = 55.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backColor,
        ), // Cambia a tu color deseado
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        onClick = {

            goToNextScreen(text)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text, color = MaterialTheme.colorScheme.inversePrimary)
            // Otros elementos de la tarjeta
        }
    }
    /*Card(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp)
            .padding(vertical = 15.dp, horizontal = 5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backColor,
        ), // Cambia a tu color deseado
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        onClick = {
            navControl.navigate(route = Screens.UsersScreen.routes)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(stringResource(id = text), color = MaterialTheme.colorScheme.secondary)
            // Otros elementos de la tarjeta
        }
    }*/
}

fun goToNextScreen(text: String) {
    when(text){
        "Turnos" -> navControl.navigate(route = Screens.TurnsScreen.routes + "/Turnos")
        "Miembros del grupo" -> navControl.navigate(route = Screens.UsersScreen.routes + "/Miembros del grupo")
        "Tipos de clientes" -> navControl.navigate(route = Screens.UsersScreen.routes + "/Tipos de clientes")
    }

}

