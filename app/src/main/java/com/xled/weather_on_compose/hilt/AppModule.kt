package com.xled.weather_on_compose.hilt

import com.xled.weather_on_compose.network.iApi
import com.xled.weather_on_compose.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideiApi(): iApi{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(iApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(iapi: iApi): WeatherRepository{
        return WeatherRepository(api = iapi)
    }
}