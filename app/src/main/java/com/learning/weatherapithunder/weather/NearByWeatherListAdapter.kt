package com.learning.weatherapithunder.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.weatherapithunder.R
import com.learning.weatherapithunder.repo.local.model.NearbyWeatherResponse


class NearByWeatherListAdapter(val nearbyWeatherResponse: NearbyWeatherResponse, val context : Context)
    : RecyclerView.Adapter<CityWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_weather,parent,false)
        return CityWeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
       return nearbyWeatherResponse.list.size
    }


    override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {
        holder.cityName.setText(nearbyWeatherResponse.list[position].name)
        holder.humidity.setText(nearbyWeatherResponse.list[position].main.humidity.toString())
        holder.pressure.setText(nearbyWeatherResponse.list[position].main.pressure.toString())
        holder.description.setText(nearbyWeatherResponse.list[position].weather.get(0).description)
        holder.temprature.setText(nearbyWeatherResponse.list[position].main.getTempInCelcius())
    }
}