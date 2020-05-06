package com.learning.weatherapithunder.repo.local.model

data class NearbyWeatherResponse(
    var message : String,

    var cod : String,

    var count : Int,

    var list : List<WeatherResponse>
)