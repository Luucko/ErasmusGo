package com.howest.erasmuswelcome.Screens

import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.howest.erasmuswelcome.CountryData

class FindPeersScreen(countrydata: CountryData) : ContentScreen {
    var countrydata=countrydata
    @Composable
    override fun DrawContent() {
        var selectedCountry by remember { mutableStateOf("") }
        val countries = countrydata.getCountryList()
        val students = mapOf(
            "Belgium" to listOf("Bastien", "Alice", "Antoine"),
            "Germany" to listOf("Hans", "Greta", "Fritz"),
            "France" to listOf("Marie", "Jean", "Luc"),
            "Spain" to listOf("Carlos", "Sofia", "Miguel"),
            "Italy" to listOf("Luca", "Giulia", "Marco")
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Find Peers",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Subtitle
            Text(
                text = "Select a country and connect with students with that nationality!",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Gray
            )

            // Dropdown for Country Selection
            CountryDropdown(
                countries = countries,
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Student List
            if (selectedCountry.isNotEmpty()) {
                StudentList(
                    title = "Fellow students from that country:",
                    students = students[selectedCountry] ?: emptyList()
                )
            }
        }
    }

    @Composable
    fun CountryDropdown(
        countries: List<String>,
        selectedCountry: String,
        onCountrySelected: (String) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .clickable { expanded = true },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = if (selectedCountry.isEmpty()) "Country" else selectedCountry,
                modifier = Modifier.padding(start = 16.dp),
                color = if (selectedCountry.isEmpty()) Color.Gray else Color.Black
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        onClick = {
                            onCountrySelected(country)
                            expanded = false
                        },
                        text = { Text(country) }
                    )
                }
            }
        }
    }

    @Composable
    fun StudentList(title: String, students: List<String>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            students.forEach { student ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = student.first().toString(),
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = student,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                    )
                }
            }
        }

    }
}
