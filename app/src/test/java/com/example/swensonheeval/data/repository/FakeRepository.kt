package com.example.swensonheeval.data.repository

import com.example.swensonheeval.data.remote.dto.ForeCastObjDto
import com.example.swensonheeval.data.remote.dto.LocationDto
import com.example.swensonheeval.domain.repository.WeatherRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.*


class FakeRepository : WeatherRepository {

    val gson = Gson()
    var foreCastjson = readFileWithoutNewLineFromResources("test.json")
    val foreCastObjDto = gson.fromJson<ForeCastObjDto>(foreCastjson, ForeCastObjDto::class.java)

    var locationsJson = readFileWithoutNewLineFromResources("test1.json")
    val locations: List<LocationDto> = gson.fromJson(locationsJson, Array<LocationDto>::class.java).toList()

    override suspend fun getWeatherForecast(q: String, days: Int): ForeCastObjDto {
        if(q=="httperror") {
            throw HttpException(Response.error<ResponseBody>(400 ,ResponseBody.create("plain/text".toMediaType(),"some content")))
        }
        if(q=="ioerror") {
            throw IOException()
        }

        return foreCastObjDto
    }

    override suspend fun getLocations(q: String): List<LocationDto> {

        if(q=="httperror") {
            throw HttpException(Response.error<ResponseBody>(400 ,ResponseBody.create("plain/text".toMediaType(),"some content")))
        }
        if(q=="ioerror") {
            throw IOException()
        }

        return locations
    }

    inline fun <reified T> loadFileText(
        caller: T,
        filePath: String
    ): String =
        T::class.java.getResource(filePath)?.readText() ?: throw IllegalArgumentException(
            "Could not find file $filePath. Make sure to put it in the correct resources folder for $caller's runtime."
        )

    @Throws(IOException::class)
    fun readFileWithoutNewLineFromResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream =
                javaClass.classLoader?.getResourceAsStream(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }

    }

}