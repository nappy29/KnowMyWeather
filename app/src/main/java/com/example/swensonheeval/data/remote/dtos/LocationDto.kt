package com.example.swensonheeval.data.remote.dtos


import com.example.swensonheeval.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("localtime")
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("tz_id")
    val tzId: String
)

fun LocationDto.toLocation(): Location {
    return Location(
        name = name,
        region = region,
        country = country
    )
}