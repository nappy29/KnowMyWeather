package com.example.swensonheeval.data.remote

import com.example.swensonheeval.data.remote.dtos.ForeCastObjDto
import com.example.swensonheeval.data.remote.dtos.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SwensonHeApi {

    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("q", encoded = true) q: String,
        @Query("days", encoded = true) days: Int
    ): ForeCastObjDto


    @GET("search.json")
    suspend fun getLocations(@Query("q") q: String): List<LocationDto>
}