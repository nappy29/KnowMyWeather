package com.example.swensonheeval.presentation.homepage

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swensonheeval.common.Resource
import com.example.swensonheeval.domain.use_case.get_forecast.GetWeatherForecastUseCase
import com.example.swensonheeval.domain.use_case.get_locations.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeatherForecastObjectState())
    val state: State<WeatherForecastObjectState> = _state


    init {
        getWeatherForecast("Kribi")
    }

    fun getWeatherForecast(cityName: String = "San francisco") {
        getWeatherForecastUseCase(cityName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = WeatherForecastObjectState(forcastObject = result.data ?: null)
                }
                is Resource.Error -> {
                    _state.value = WeatherForecastObjectState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = WeatherForecastObjectState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}