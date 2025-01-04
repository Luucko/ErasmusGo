package com.howest.erasmuswelcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.howest.erasmuswelcome.ui.theme.ErasmuswelcomeTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Info
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private var user: DBHelper.User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DBHelper(this)

        setContent {
            ErasmuswelcomeTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController, startDestination = "start") {
                        composable("start") {
                            MainScreen(navController = navController)
                        }
                        composable("home") {
                            //HomeScreen(navController = navController)
                        }
                        composable("pokedex") {
                            //PokeDex(navController = navController, user!!.name, dbHelper)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar()
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { innerPadding ->
        // Main content goes here
        Box(modifier = Modifier.padding(innerPadding)) {
            Greeting("Erasmus Student")
        }
    }
}

@Composable
fun CustomTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* Handle navigation click */ }) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
        Text(
            text = "Erasmus Welcome",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
        IconButton(onClick = { /* Handle profile click */ }) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White)
        }
    }
}


@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        IconButton(onClick = { /* Handle Home click */ }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { /* Handle Map click */ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Map",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { /* Handle Info click */ }) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name!")
    }
}
