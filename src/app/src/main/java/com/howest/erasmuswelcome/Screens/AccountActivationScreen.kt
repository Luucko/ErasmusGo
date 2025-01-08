package com.howest.erasmuswelcome.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.howest.erasmuswelcome.ContentScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AccountActivationScreen: ContentScreen {
    @Composable
    override fun DrawContent() {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                BasicText(
                    text = "How to Activate your\nPersonal Student Account",
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                // Body text
                BasicText(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Justify
                    )
                )

                // Steps
                val steps = listOf(
                    "Go to activate-account.ipca.pt",
                    "Some step content.",
                    "Some step content.",
                    "Some step content.",
                    "Some step content.",
                    "You're all set!"
                )

                steps.forEachIndexed { index, step ->
                    BasicText(
                        text = "${index + 1}. $step",
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

    }


}