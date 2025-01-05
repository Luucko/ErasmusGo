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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
                        composable("login") {
                            LoginScreen(dbHelper, navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }
                        composable("new_student") {
                            //NewStudentScreen(navController = navController)
                        }
                        composable("my_account") {
                            //MyAccountScreen(navController = navController)
                        }
                        composable("communication") {
                            //AcademicsScreen(navController = navController)
                        }
                        composable("campus_life") {
                            //CampusLifeScreen(navController = navController)
                        }
                        composable("language") {
                            //LanguageScreen(navController = navController)
                        }
                        composable("events") {
                            //EventsScreen(navController = navController)
                        }
                        composable("transportation_and_discounts") {
                            //TransportationScreen(navController = navController)
                        }
                        composable("admin_tools") {
                            //AdminToolsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun LoginScreen(dbHelper: DBHelper, navController: NavController) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var loading by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        loading = true
                        dbHelper.authenticateUser(email, password) { success, message ->
                            loading = false
                            if (success) {
                                navController.navigate("start")
                            } else {
                                errorMessage = message
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !loading
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Login")
                    }
                }

                TextButton(onClick = { navController.navigate("register") }) {
                    Text("I am a new student!")
                }
            }
        }
    }
    @Composable
    fun RegisterScreen(navController: NavController) {

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
                text = "ErasmusGO",
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
                "home" to "start",
                "login" to "login",
                "teachers" to "communication",
                "map" to "campus_life",
                "first_steps" to "new_student",
                "account_setup" to "my_account",
                "meetings" to "communication",
                "events" to "events",
                "calendar" to "campus_life",
                "other_students" to "admin_tools",
                "language" to "language",
                "transport" to "transportation_and_discounts",
                "discounts" to "transportation_and_discounts"
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


}
