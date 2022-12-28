package com.example.swensonheeval.data.remote.dto

import com.example.swensonheeval.domain.model.ForcastObject

data class ForecastObjectDto(
    val current: Current,
    val forecast: Forecast,
    val location: CurrentLocation
)

fun ForecastObjectDto.toForecastObject(): ForcastObject {

    return ForcastObject(
        date = this.location.localtime,
        location = location.name,
        days = forecast.forecastdays.map { it.toDay() }
    )
}