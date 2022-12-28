package com.example.swensonheeval.domain.use_case.get_forecast

import android.util.Log
import com.example.swensonheeval.common.Resource
import com.example.swensonheeval.data.remote.dto.toForecastObject
import com.example.swensonheeval.data.remote.dtos.toForecastObject
import com.example.swensonheeval.domain.model.ForcastObject
import com.example.swensonheeval.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(q: String, days: Int=3): Flow<Resource<ForcastObject>> = flow{
        try {
            emit(Resource.Loading<ForcastObject>())
            val forecastObject = weatherRepository.getWeatherForecast(q, days).toForecastObject()
            Log.d("StateObjnumber2", forecastObject.toString())
            emit(Resource.Success<ForcastObject>(forecastObject))
        } catch(e: HttpException) {
            emit(Resource.Error<ForcastObject>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ForcastObject>("Couldn't reach server. Check your internet connection."))
        }
    }
}