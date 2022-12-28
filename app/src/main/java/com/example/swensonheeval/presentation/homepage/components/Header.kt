package com.example.swensonheeval.presentation.homepage.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swensonheeval.common.Utils

@Composable
fun Header(
    cityName: String,
    date: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = cityName,
            modifier = Modifier.padding(1.dp),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = Utils.changeDateFormat(dateString = date),
            fontSize = 14.sp,
            color = Color.White
        )
    }
}