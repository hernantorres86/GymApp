package com.hernan.gymapp.ui_app

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hernan.gymapp.R
import com.hernan.gymapp.funtions.userList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
private var bottomState: BottomSheetScaffoldState? = null
var coroutineScoper: CoroutineScope? = null
private var textScreen: String? = null
private lateinit var navControl: NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(isdarkTheme: Boolean, navController: NavHostController, text: String?) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    bottomState = bottomSheetScaffoldState
    coroutineScoper = coroutineScope

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {

                       goToUserInfo(text = "")
            /*Column {
                // Aquí puedes poner el contenido de tu bottom sheet
                Button(onClick = { /* Handle bottom sheet item click */ }) {
                    // Botón dentro del bottom sheet
                }
            }*/
        },
        content = {
            // Contenido principal de la pantalla
            /*Button(onClick = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }) {
                // Botón para mostrar el bottom sheet
            }*/
            Scaffold(
                topBar = { ToolbarUsers()},
                content = {ColumnToList()}
            )
        }
    )


    textScreen = text
    navControl = navController
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarUsers() {

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
fun ColumnToList() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 16.dp),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.scrim)

        ) {

        items(userList()) { item ->
            UserList(item.drawable, item.text)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(drawable: Int, text: Int) {
    val bottomSheetHeight = with(LocalDensity.current) { 400.dp.toPx() }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ), // Cambia a tu color deseado
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        onClick = {
            //goToNextScreen("text")
            coroutineScoper!!.launch {
                bottomState!!.bottomSheetState.expand()
            }

        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(drawable),  //R.drawable.ab1_inversions
                contentDescription = null,
                contentScale = ContentScale.Crop, // puedo dar 3 estilos de imagen. Fit, FillBounds y Crop
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(horizontal = 30.dp))
            Text(stringResource(id = text),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.surfaceTint)

            // Otros elementos de la tarjeta
        }
    }
}
@Composable
private fun goToUserInfo(text: String) {

    val bottomSheetHeight = with(LocalDensity.current) { 20.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var bottomSheetVisible by remember { mutableStateOf(true) }

        val targetHeight = if (bottomSheetVisible) bottomSheetHeight else 0f
        val animatableHeight = remember { androidx.compose.animation.core.Animatable(targetHeight) }
        LaunchedEffect(bottomSheetVisible) {
            animatableHeight.animateTo(
                targetValue = targetHeight,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        }

        Column(
            Modifier
                .graphicsLayer {
                    translationY = animatableHeight.value
                }
                .fillMaxSize()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Image(
                painter = painterResource(R.drawable.ab3_stretching),  //R.drawable.ab1_inversions
                contentDescription = null,
                contentScale = ContentScale.Crop, // puedo dar 3 estilos de imagen. Fit, FillBounds y Crop
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .padding(15.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Text("Main Content")
        }

        // Toggle bottom sheet visibility
        coroutineScoper!!.launch {
            bottomSheetVisible = !bottomSheetVisible
        }
    }
}
    //navControl.navigate(route = Screens.BottomDialogUserData.routes + "/$text")

