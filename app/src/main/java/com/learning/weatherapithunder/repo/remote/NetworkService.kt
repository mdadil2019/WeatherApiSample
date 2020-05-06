package com.learning.weatherapithunder.repo.remote

import com.learning.weatherapithunder.repo.local.model.NearbyWeatherResponse
import com.learning.weatherapithunder.repo.local.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("weather")
    fun queryWeather(
        @Query("q") cityName : String,
        @Query("appid") appId : String = EndPoints.API_KEY
    ): Single<WeatherResponse>

    @GET("find")
    fun queryNearbyCitiesWeather(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("cnt") noOfNearByCities : Int,
        @Query("appid") appId : String = EndPoints.API_KEY
    ) : Single<NearbyWeatherResponse>
}