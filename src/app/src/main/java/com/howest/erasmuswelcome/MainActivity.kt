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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.material3.ExposedDropdownMenuDefaults
import com.google.firebase.database.FirebaseDatabase
import com.howest.erasmuswelcome.CountryFetcher.Companion.fetchCountries


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

                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(dbHelper, navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(dbHelper, navController = navController)
                        }
                        composable("start") {
                            MainScreen(navController = navController)
                        }
                        composable("new_student") {
                            //NewStudentScreen(navController = navController)
                        }
                        composable("my_account") {
                            //MyAccountScreen(navController = navController)
                        }
                        composable("communication") {
                            ContactTeacherScreen(navController = navController)
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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContactTeacherScreen(navController: NavController) {
        var teacherDB: FirebaseDBHelper = FirebaseDBHelper()
        var teacherList: List<FirebaseDBHelper.Teacher> = teacherDB.getTeachers()

        var selectedDegree by remember { mutableStateOf("") }
        var selectedCourse by remember { mutableStateOf("") }
        var expandedDegree by remember { mutableStateOf(false) }
        var expandedCourse by remember { mutableStateOf(false) }
        var degrees: ArrayList<String> = ArrayList<String>();
        teacherList.forEach { e ->
            degrees.addAll(e.degree)
        }
        var courses: ArrayList<String> = ArrayList<String>();
        teacherList.forEach { e ->
            courses.addAll(e.courses)
        }



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
                ExposedDropdownMenuBox(
                    expanded = expandedDegree,
                    onExpandedChange = { expandedDegree = !expandedDegree }
                ) {
                    OutlinedTextField(
                        value = selectedDegree,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Degree") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDegree)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedDegree,
                        onDismissRequest = { expandedDegree = false }
                    ) {
                        degrees.forEach { degree ->
                            DropdownMenuItem(
                                text = { Text(degree) },
                                onClick = {
                                    selectedDegree = degree
                                    expandedDegree = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandedCourse,
                    onExpandedChange = { expandedCourse = !expandedCourse }
                ) {
                    OutlinedTextField(
                        value = selectedCourse,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Course") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCourse)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCourse,
                        onDismissRequest = { expandedCourse = false }
                    ) {
                        courses.forEach { course ->
                            DropdownMenuItem(
                                text = { Text(course) },
                                onClick = {
                                    selectedCourse = course
                                    expandedCourse = false
                                }
                            )
                        }


                    }


                }

                if (selectedDegree.isNotEmpty() && selectedCourse.isNotEmpty()) {
                    var selectedTeacher: FirebaseDBHelper.Teacher? = null

                    teacherList.forEach { teacher ->
                        if (teacher.courses.contains(selectedCourse) && teacher.degree.contains(
                                selectedDegree
                            )
                        ) {
                            selectedTeacher = teacher
                        }
                    }
                    if (selectedTeacher != null) {

                        Text(
                            text = selectedTeacher!!.name

                        )
                        Text(
                            text = "Email:"+ selectedTeacher!!.email

                        )
                    }
                }

            }

        }
    }

    @Composable
    fun LoginScreen(dbHelper: DBHelper, navController: NavController) {
        var name by remember { mutableStateOf("") }
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
                    text = "Welcome to ErasmusGO",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
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
                        errorMessage = null
                        try {
                            // Validate credentials with DBHelper
                            val isMatch = dbHelper.checkPasswordMatch(name, password)
                            if (isMatch) {
                                user = dbHelper.getUserByUsername(name)
                                navController.navigate("start")
                            } else {
                                errorMessage = "Invalid username or password"
                            }
                        } catch (e: Exception) {
                            errorMessage = "Error during login: ${e.message}"
                        } finally {
                            loading = false
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
                    Text("Don't have an account? Register")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterScreen(dbHelper: DBHelper, navController: NavController) {
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("Portugal") }
        var email by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var successMessage by remember { mutableStateOf<String?>(null) }
        var loading by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        var countryList by remember { mutableStateOf<List<String>>(emptyList()) }

        // Fetch the countries using CountryFetcher

        LaunchedEffect(countryList) {
            fetchCountries { fetchedCountries ->
                if (fetchedCountries != null) {
                    countryList = fetchedCountries

                } else {
                    errorMessage = "Failed to load countries"

                }
            }
        }

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
                    text = "Register",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
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

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                // Country Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = country,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Country") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        println("liste wird geprintet")
                        println(countryList)

                        countryList.forEach { countryName ->
                            DropdownMenuItem(
                                text = { Text(countryName) },
                                onClick = {
                                    country = countryName
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (successMessage != null) {
                    Text(
                        text = successMessage!!,
                        color = Color.Green,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        if (name.isBlank() || password.isBlank() || confirmPassword.isBlank() || country.isBlank() || email.isBlank()) {
                            errorMessage = "All fields are required!"
                            return@Button
                        }

                        if (password != confirmPassword) {
                            errorMessage = "Passwords do not match!"
                            return@Button
                        }

                        loading = true
                        errorMessage = null
                        try {
                            dbHelper.addUser(name, password, country, email)
                            successMessage = "User registered successfully!"
                            loading = false
                        } catch (e: Exception) {
                            errorMessage = "Registration failed: ${e.message}"
                            loading = false
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
                        Text("Register")
                    }
                }

                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Already have an account? Login")
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
                    user?.name?.let { Greeting(name = it) }
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
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.White
                )
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
            Text(
                "Menu",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            val menuItems = listOf(
                "home" to "start",
                "login" to "login",
                "communication" to "communication",
                "calender" to "campus_life",
                "campus_map" to "campus_life",
                "my_account" to "my_account",
                "meetings" to "communication",
                "events" to "events",
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
            IconButton(onClick = { navController.navigate("start") }) {
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
