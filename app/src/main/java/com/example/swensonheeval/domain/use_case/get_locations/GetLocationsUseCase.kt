package com.example.swensonheeval.domain.use_case.get_locations

import com.example.swensonheeval.common.Resource
import com.example.swensonheeval.data.remote.dtos.toLocation
import com.example.swensonheeval.domain.model.Location
import com.example.swensonheeval.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(q: String): Flow<Resource<List<Location>>> = flow {
        try {
            emit(Resource.Loading())
            delay(3000)
            val locations = weatherRepository.getLocations(q).map { it.toLocation() }
            emit(Resource.Success(data = locations))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}