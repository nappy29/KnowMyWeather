package com.example.swensonheeval.domain.use_case.get_forecast

import com.example.swensonheeval.data.repository.FakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetWeatherForecastUseCaseTest{

    private lateinit var getWeatherForecastUseCase: GetWeatherForecastUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getWeatherForecastUseCase = GetWeatherForecastUseCase(fakeRepository)
    }

    @Test
    fun `weather forecast, correct results`() = runBlocking {
        val result = getWeatherForecastUseCase("Reutov").last()

        assertThat(result).isNotNull()
        assertThat(result.data?.days?.size).isGreaterThan(0)
        assertThat(result.data?.days?.get(0)?.mph).isEqualTo(6.5)

    }

    @Test
    fun `weather forecast, Http Exception error`() = runBlocking {
        val result = getWeatherForecastUseCase("httperror").last()
        assertThat(result.data).isNull()
        assertThat(result.message).isNotNull()
        assertThat(result.message).isEqualTo("HTTP 400 Response.error()")
    }

    @Test
    fun `weather forecast, IO Exception error`() = runBlocking {
        val result = getWeatherForecastUseCase("ioerror").last()
        assertThat(result.data).isNull()
        assertThat(result.message).isNotNull()
        assertThat(result.message).isEqualTo("Couldn't reach server. Check your internet connection.")
    }

}