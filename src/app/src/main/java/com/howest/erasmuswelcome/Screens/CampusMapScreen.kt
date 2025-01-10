package com.howest.erasmuswelcome.Screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter

class CampusMapScreen : ContentScreen {
    @Composable
    override fun DrawContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.Text(
                text = "Campus Map",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            // Sections for each campus
            CampusSection(title = "Map of IPCA Campus Management")
            Spacer(modifier = Modifier.height(24.dp))
            CampusSection(title = "Map of IPCA Campus Tourism")
            Spacer(modifier = Modifier.height(24.dp))
            CampusSection(title = "Map of IPCA Campus Technology")
        }
    }

    @Composable
    fun CampusSection(title: String) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            // Title for each section
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Placeholder for the image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder text inside the box
                val imageUrl = "https://ipca.pt/wp-content/uploads/2017/10/Campus-IPCA-2021.jpg"
                val painter = rememberImagePainter(imageUrl)


                Image(painter = painter, contentDescription = "Campus IPCA")
            }
        }
    }

}
