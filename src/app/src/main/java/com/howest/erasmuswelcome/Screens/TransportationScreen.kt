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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class TransportationScreen : ContentScreen {
    @Composable
    override fun DrawContent() {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Map Section
                Text(
                    text = "Map which situates bus stop\nand train station closest to Campus:",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder for map (a simple cross)
                    Text(text = "X", color = Color.White)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Bus Lines Section
                Text(
                    text = "Connecting Bus Lines to the Campus' bus stop:",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
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
                        repeat(4) {
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

                Spacer(modifier = Modifier.height(32.dp))

                // Train Routes Section
                Text(
                    text = "Below you find a list of connecting Train Routes to the Train Station of Barcelos:",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
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
                        repeat(4) {
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
