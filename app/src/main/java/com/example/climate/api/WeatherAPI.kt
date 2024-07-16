package com.example.climate.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import com.example.climate.WeatherPage
interface WeatherAPI {
    @GET("v1/current.json")
     suspend fun getWeather(
        @Query("key") apikey : String,
        @Query("q") city : String
    ):Response<WeatherModel>
}