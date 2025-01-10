package com.howest.erasmuswelcome.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.howest.erasmuswelcome.Screens.EventsScreen.Event
import kotlinx.coroutines.tasks.await

class LanguageScreen : ContentScreen {

    data class Translation(val portuguese: String = "", val english: String = "")

    private suspend fun fetchTranslations(): List<Translation> {
        val database = Firebase.database
        val languageRef = database.getReference("Language/translations")
        val language = mutableListOf<Translation>()

        try {
            val snapshot = languageRef.get().await()
            for (child in snapshot.children) {
                val translation = child.getValue(Translation::class.java)
                if (translation != null) {
                    language.add(translation)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return language
    }

    @Composable
    override fun DrawContent() {

        val language = remember { mutableStateListOf<Translation>() }

        // Lade die Events von der Realtime Database
        LaunchedEffect(Unit) {
            val fetchedEvents = fetchTranslations()
            language.addAll(fetchedEvents)
        }
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Learn Portuguese",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Useful vocabulary:",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(512.dp)
                        .background(Color.LightGray,
                            shape= RoundedCornerShape(8.dp)) // Leichtes Grau für ein freundlicheres Erscheinungsbild
                        .padding(16.dp) // Padding für mehr Abstand zum Rand

                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp), // Mehr Abstand zwischen den Einträgen
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(language) { translation ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(8.dp) // Abgerundete Ecken für jeden Eintrag
                                    )
                                    .padding(12.dp) // Inneres Padding für die Zeilen
                            ) {
                                Text(
                                    text = translation.english,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold // Fett für bessere Lesbarkeit
                                    )
                                )
                                Text(
                                    text = " = ",
                                    style = TextStyle(
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = translation.portuguese,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic // Kursiv für den Übersetzungstext
                                    )
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Learn more:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                val context = LocalContext.current
                val url = "https://www.practiceportuguese.com/shorties/"
                ClickableText(

                    text = AnnotatedString("https://www.practiceportuguese.com/shorties/"),
                    style = MaterialTheme.typography.bodyLarge,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}
