package com.example.swensonheeval.presentation.homepage.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swensonheeval.common.Utils
import com.example.swensonheeval.domain.model.Day

@Composable
fun Footer(
    days: List<Day>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 95.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            dayElement(days[0].apply {
                date = "Today"
            })
            dayElement(days[1].apply {
                date = "Tomorrow"
            })
            dayElement(days[2].apply {
                var d = date
                date = Utils.changeDateFormat(
                    currentFormat = "yyyy-MM-dd", requiredFormat = "EEEE", dateString = d
                )
            })

        }
    }
}

@Composable
fun dayElement(
    day: Day
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https:${day.imageUrl}")
                .crossfade(true).build(),
            modifier = Modifier.size(40.dp),
            contentDescription = null
        )
        Text(
            text = "${day.tempCel}°/${day.tempFah}°F",
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.alpha(ContentAlpha.medium)
        )
        Text(
            text = day.date,
            fontSize = 12.sp,
            color = Color.White,

            )
    }

}