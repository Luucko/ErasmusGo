package com.howest.erasmuswelcome.Screens

import androidx.compose.runtime.Composable
import com.howest.erasmuswelcome.ContentScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DiscountScreen : ContentScreen {

    data class discountItem(
        val title:String="",
        val description:String="",
        val disountPercentage: Int =0

    )
    @Composable
    override fun DrawContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Student Discounts",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "Currently, IPCA students qualify for these discounts:",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Gray
            )

            // Discount List
            DiscountList(
                items = listOf(
                    "Discount Item 1",
                    "Discount Item 2",
                    "Discount Item 3",
                    "Discount Item 4",
                    "Discount Item 5",
                    "Discount Item 6",
                    "Discount Item 7"
                )
            )

            // Footer
            Text(
                text = "Come back later for more discounts!",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Gray
            )
        }
    }

    @Composable
    fun DiscountList(items: List<String>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Circular icon
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "A",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                            color = Color.White
                        )
                    }

                    // Text
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                    )
                }
            }
        }
    }

}
