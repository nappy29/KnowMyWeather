package com.example.swensonheeval.presentation.homepage

import com.example.swensonheeval.domain.model.ForcastObject

data class WeatherForecastObjectState(
    val isLoading: Boolean = false,
    val forcastObject: ForcastObject? = null,
    val error: String? = null
)