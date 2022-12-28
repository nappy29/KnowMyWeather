package com.example.swensonheeval.domain.repository

import com.example.swensonheeval.data.remote.dtos.ForeCastObjDto
import com.example.swensonheeval.data.remote.dtos.LocationDto

interface WeatherRepository {

    suspend fun getWeatherForecast(q: String = "San francisco", days: Int = 3): ForeCastObjDto


    suspend fun getLocations(q: String): List<LocationDto>
}