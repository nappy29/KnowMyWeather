package com.example.swensonheeval.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastdays: List<Forecastday>
)