package com.example.swensonheeval.domain.repository

import com.example.swensonheeval.data.remote.dto.ForeCastObjDto
import com.example.swensonheeval.data.remote.dto.LocationDto

interface WeatherRepository {

    suspend fun getWeatherForecast(q: String = "San francisco", days: Int = 3): ForeCastObjDto


    suspend fun getLocations(q: String): List<LocationDto>
}