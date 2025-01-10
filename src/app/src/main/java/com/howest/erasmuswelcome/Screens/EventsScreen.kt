package com.howest.erasmuswelcome.Screens

import android.content.ContentValues.TAG
import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
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

    private suspend fun fetchEvents(): List<Event> {
        val database = Firebase.database
        val eventsRef = database.getReference("Events")
        val events = mutableListOf<Event>()

        try {
            val snapshot = eventsRef.get().await()
            for (child in snapshot.children) {
                val event = child.getValue(Event::class.java)
                if (event != null) {
                    events.add(event)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return events
    }


    @Composable
    override fun DrawContent() {

        val events = remember { mutableStateListOf<Event>() }

        // Lade die Events von der Realtime Database
        LaunchedEffect(Unit) {
            val fetchedEvents = fetchEvents()
            events.addAll(fetchedEvents)
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
                EventList(events)
            }
        }
    }
    @Composable
    fun EventList(events: List<Event>) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(events) { event ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = event.title, style = MaterialTheme.typography.bodyLarge)
                        Text(text = event.date, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }

}
