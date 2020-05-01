package com.learning.weatherapithunder.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.weatherapithunder.repo.local.model.WeatherResponse
import com.learning.weatherapithunder.repo.remote.EndPoints
import com.learning.weatherapithunder.repo.remote.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class WeatherActivityViewModel : ViewModel(){

    val cityWeather = MutableLiveData<WeatherResponse>()


    //Make request to repository for new Weather Updates
    fun updateWeather(cityName : String){
        val file = File("")
      var disposable =   Networking.create(EndPoints.baseUrl,file,2000)
            .queryWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cityWeather.postValue(it)
            },{
                Log.e("Error : ", "$it")
            })
    }
}