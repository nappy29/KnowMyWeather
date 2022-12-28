package com.example.swensonheeval.data.remote.dto

import com.example.swensonheeval.domain.model.Day

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: DayDto,
    val hour: List<Hour>
)

fun Forecastday.toDay(): Day {
    return Day(
        date = date,
        tempCel = day.avgtemp_c,
        tempFah = day.avgtemp_f,
        condition = day.condition.text,
        mph = day.maxwind_mph,
        humidity = day.avghumidity,
        imageUrl = day.condition.icon
        )
}