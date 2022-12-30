package com.example.swensonheeval.domain.use_case.get_locations

import com.example.swensonheeval.data.repository.FakeRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetLocationsUseCaseTest{
    private lateinit var getLocationsUseCase: GetLocationsUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getLocationsUseCase = GetLocationsUseCase(fakeRepository)
    }

    @Test
    fun `search location, correct results`() = runBlocking {
        val result = getLocationsUseCase("Reutov").last()

        assertThat(result).isNotNull()
        assertThat(result.data?.size).isGreaterThan(0)
        assertThat(result::class.java.typeName).contains("Success")
    }

    @Test
    fun `search location, Http Exception error`() = runBlocking {
        val result = getLocationsUseCase("httperror").last()
        assertThat(result.data).isNull()
        assertThat(result.message).isNotNull()
        assertThat(result.message).isEqualTo("HTTP 400 Response.error()")
        assertThat(result::class.java.typeName).contains("Error")
    }

    @Test
    fun `search location, IO Exception error`() = runBlocking {
        val result = getLocationsUseCase("ioerror").last()
        assertThat(result.data).isNull()
        assertThat(result.message).isNotNull()
        assertThat(result.message).isEqualTo("Couldn't reach server. Check your internet connection.")
    }

}