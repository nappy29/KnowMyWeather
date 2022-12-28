package com.example.swensonheeval.presentation.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swensonheeval.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swensonheeval.domain.model.Day

@Composable
fun Center(
    day: Day
) {

    Box(
        contentAlignment = Alignment.Center, // you apply alignment to all children
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https:${day.imageUrl}")
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(94.dp),
                contentDescription = null
            )
            Text(
                text = "${day.tempFah}°F",
                modifier = Modifier.padding(bottom = 23.dp),
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "It's a ${day.condition} day",
                fontSize = 13.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 110.dp, end = 110.dp, top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box {
                    Row{
                        Image(
                            painterResource(R.drawable.wind_speed),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(23.dp)
                        )
                        Text(
                            text = "${day.mph} mph",
                            fontSize = 11.sp,
                            color = Color.White,
                            modifier = Modifier
                                .alpha(ContentAlpha.medium)
                                .padding(start = 2.dp)
                        )
                    }
                }

                Box {
                    Row {
                        Image(
                            painterResource(R.drawable.humidity),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(23.dp)
                        )

                        Text(
                            text = "${day.humidity}%",
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier
                                .alpha(ContentAlpha.medium)
                        )
                    }
                }
            }
        }
    }
}