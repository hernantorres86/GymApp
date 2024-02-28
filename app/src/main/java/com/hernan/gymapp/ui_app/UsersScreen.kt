package com.hernan.gymapp.ui_app

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hernan.gymapp.R
import com.hernan.gymapp.funtions.userList


private var textScreen: String? = null
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(isdarkTheme: Boolean, navController: NavHostController, text: String?) {

    Scaffold(
        topBar = { ToolbarUsers()},
        content = {ColumnToList()}
    )

    textScreen = text
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
@Composable
fun UserList(drawable: Int, text: Int) {

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
        )
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