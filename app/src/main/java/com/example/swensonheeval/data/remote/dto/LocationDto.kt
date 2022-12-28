package com.example.swensonheeval.data.remote.dto

import com.example.swensonheeval.domain.model.Location

data class LocationDto(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)

fun LocationDto.toLocation(): Location{
    return Location(
        name = name,
        region = region,
        country = country
    )
}