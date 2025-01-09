package com.howest.erasmuswelcome.Screens

import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EventsScreen : ContentScreen {

    data class Event(
        val id: String = "",
        val title: String = "",
        val description: String = "",
        val date: String = ""
    )

    // Firebase-Daten abrufen
    suspend fun fetchEvents(): List<Event> {
        val db = FirebaseFirestore.getInstance()
        val events = mutableListOf<Event>()

        try {
            val result = db.collection("events").get().await()
            for (document in result) {
                val event = document.toObject(Event::class.java)
                println(event.title)
                events.add(event)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return events

    }
    @Composable
    override fun DrawContent() {
        val events = remember { mutableStateListOf<Event>() }
        val coroutineScope = rememberCoroutineScope()

        // Lade Events bei der Initialisierung
        LaunchedEffect(Unit) {
            coroutineScope.launch(Dispatchers.IO) {
                val fetchedEvents = fetchEvents()
                events.addAll(fetchedEvents)
            }
        }
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Upcoming Events",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Big Upcoming Activity
                Text(
                    text = "Big upcoming activity:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder for the image or activity representation
                    Text(text = "X", color = Color.White)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Other Upcoming Events Section
                Text(
                    text = "Other upcoming events",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Gray)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                         events.forEach { event ->
                             Row(
                                 verticalAlignment = Alignment.CenterVertically,
                                 horizontalArrangement = Arrangement.spacedBy(16.dp)
                             ) {
                                 Box(
                                     modifier = Modifier
                                         .size(40.dp)
                                         .background(Color.White, shape = CircleShape)
                                 ) {
                                     Text(
                                         text = "A",
                                         modifier = Modifier.align(Alignment.Center)
                                     )
                                 }
                                 Text(text = "List item")
                             }
                         }
                    }


                }
            }
        }
    }
}
