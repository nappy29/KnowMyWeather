package com.example.swensonheeval.di

import com.example.swensonheeval.BuildConfig
import com.example.swensonheeval.data.remote.SwensonHeApi
import com.example.swensonheeval.data.repository.WeatherRepositoryImp
import com.example.swensonheeval.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        val apiInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", BuildConfig.APIKEY)
                .build()

            val reqBuilder = original.newBuilder()
                .url(url)
            chain.proceed(reqBuilder.build())
        }

        val aLogger = HttpLoggingInterceptor()
        aLogger.level = (HttpLoggingInterceptor.Level.BASIC)

        val level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(aLogger)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): SwensonHeApi =
        retrofit.create(SwensonHeApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherRepository(api: SwensonHeApi): WeatherRepository {
        return WeatherRepositoryImp(api)
    }
}