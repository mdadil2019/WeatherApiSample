package com.learning.weatherapithunder.weather

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_weather.view.*

class CityWeatherViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var cityName  = itemView.tv_cityName
    var temprature  = itemView.tv_temprature
    var humidity  = itemView.tv_humidity
    var pressure  = itemView.tv_pressure
    var description  = itemView.tv_description
}
