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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

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
                        composable("calender") {
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            SideMenu(navController = navController, drawerState = drawerState)
        }
    ) {
        Scaffold(
            topBar = {
                CustomTopBar {
                    scope.launch {
                        drawerState.open()
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            // Main content goes here
            Box(modifier = Modifier.padding(innerPadding)) {
                Greeting("Erasmus Student")
            }
        }
    }
}

@Composable
fun CustomTopBar(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onMenuClick) {
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
fun SideMenu(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text("Menu", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))

        val menuItems = listOf(
            "Home" to "home",
            "Info" to "info",
            "Communicating with Teachers" to "teachers",
            "Map of Buildings and Rooms" to "map",
            "First Steps at University" to "first_steps",
            "Student Account Setup" to "account_setup",
            "Important Meetings" to "meetings",
            "Events Together" to "events",
            "School Calendar" to "calendar",
            "Other Students Info" to "other_students",
            "Language Info" to "language",
            "Public Transportation" to "transport",
            "Discount List" to "discounts"
        )

        menuItems.forEach { (title, route) ->
            TextButton(onClick = {
                scope.launch {
                    drawerState.close()
                }
                navController.navigate(route)
            }) {
                Text(text = title, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        IconButton(onClick = { navController.navigate("home") }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { navController.navigate("map") }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Map",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { navController.navigate("info") }) {
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
