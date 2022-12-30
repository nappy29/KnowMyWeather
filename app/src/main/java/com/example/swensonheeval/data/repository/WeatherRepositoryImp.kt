package com.example.swensonheeval.data.repository

import com.example.swensonheeval.data.remote.SwensonHeApi
import com.example.swensonheeval.data.remote.dto.LocationDto
import com.example.swensonheeval.data.remote.dto.ForeCastObjDto
import com.example.swensonheeval.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: SwensonHeApi
) : WeatherRepository {

    override suspend fun getWeatherForecast(q: String, days: Int): ForeCastObjDto {
        return api.getWeatherForecast(q, days)
    }

    override suspend fun getLocations(q: String): List<LocationDto> {
        return api.getLocations(q)
    }
}