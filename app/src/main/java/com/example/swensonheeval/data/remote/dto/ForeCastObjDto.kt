package com.example.swensonheeval.data.remote.dto

import com.example.swensonheeval.domain.model.ForcastObject
import com.google.gson.annotations.SerializedName

data class ForeCastObjDto(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: LocationDto
)

fun ForeCastObjDto.toForecastObject(): ForcastObject {

    return ForcastObject(
        date = this.location.localtime,
        location = location.name,
        days = forecast.forecastdays.map { it.toDay() }
    )
}