package com.hernan.gymapp.user_type.data.data.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hernan.gymapp.R
import com.hernan.gymapp.navigation.Screens


private var textScreen: String? = null
private lateinit var navControl: NavHostController
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersTypeScreen(isdarkTheme: Boolean, navController: NavHostController, text: String?) {

    Scaffold(
        topBar = { ToolbarUsersType() },
        content = { ColumnToListTypes() }
    )

    textScreen = text
    navControl = navController
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarUsersType() {

        TopAppBar(
            title = {
                textScreen?.let {
                    Text(text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colorResource(id = R.color.white))
                }

            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.scrim)

        )

}

@Composable
fun ColumnToListTypes() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(vertical = 70.dp, horizontal = 16.dp),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.scrim)

        ) {

        item { UserListTypesUsers(R.drawable.fitness_man_one, "Categoria Light") }
        item { UserListTypesUsers(R.drawable.fitness_woman_two, "Categoria Force") }
        item { UserListTypesUsers(R.drawable.fitness_man_two, "Categoria Stronger") }

        }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListTypesUsers(drawable: Int, text: String) {

    Card(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ), // Cambia a tu color deseado
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        onClick = {
            // funcion para pasar a la siguiente pantalla
            goToUserInfo(text)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(drawable),  //R.drawable.ab1_inversions
                contentDescription = null,
                contentScale = ContentScale.Crop, // puedo dar 3 estilos de imagen. Fit, FillBounds y Crop
                modifier = Modifier
                    .fillMaxSize()
                    .clip(AbsoluteRoundedCornerShape(16.dp))
            )
            //Spacer(modifier = Modifier.padding(horizontal = 30.dp))
            Text(text,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.padding(start = 20.dp, bottom = 16.dp).align(Alignment.BottomStart))

            // Otros elementos de la tarjeta
        }
    }
}

private fun goToUserInfo(text: String) {

        navControl.navigate(route = Screens.UsersScreen.routes + "/$text")



}
