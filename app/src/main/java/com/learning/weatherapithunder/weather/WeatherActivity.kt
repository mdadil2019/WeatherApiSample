package com.learning.weatherapithunder.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.weatherapithunder.R
import com.learning.weatherapithunder.databinding.ActivityMainBinding
import com.learning.weatherapithunder.repo.local.model.NearbyWeatherResponse
import com.learning.weatherapithunder.repo.remote.EndPoints
import com.learning.weatherapithunder.repo.remote.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class WeatherActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var nearbyWeatherResponseAdapter : NearByWeatherListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(WeatherActivityViewModel::class.java)

        btn_fetch_all_weather.setOnClickListener{
            Networking.create(EndPoints.baseUrl, File(""), 1024)
                .queryNearbyCitiesWeather(26.84,80.94,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    nearbyWeatherResponseAdapter = NearByWeatherListAdapter(it,this)
                    binding.recyclerView.adapter = nearbyWeatherResponseAdapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                },{
                    Log.e("Error : ", "$it")
                })
        }


    }
}
