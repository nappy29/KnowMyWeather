package com.example.swensonheeval.data.remote.dto


import com.example.swensonheeval.domain.model.Day
import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("astro")
    val astro: Astro,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val day: DayDto,
    @SerializedName("hour")
    val hour: List<Hour>
)
fun Forecastday.toDay(): Day {
    return Day(
        date = date,
        tempCel = day.avgtempC,
        tempFah = day.avgtempF,
        condition = day.condition.text,
        mph = day.maxwindMph,
        humidity = day.avghumidity,
        imageUrl = day.condition.icon
    )
}