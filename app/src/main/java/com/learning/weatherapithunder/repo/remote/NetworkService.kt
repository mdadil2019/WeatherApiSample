package com.learning.weatherapithunder.repo.remote

import com.learning.weatherapithunder.repo.local.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET
    fun queryWeather(
        @Query("") cityName : String,
        @Query("appid") appId : String = EndPoints.API_KEY
    ): Single<WeatherResponse>
}