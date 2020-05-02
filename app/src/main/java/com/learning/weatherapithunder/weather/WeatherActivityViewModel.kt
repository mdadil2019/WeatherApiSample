package com.learning.weatherapithunder.weather

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.weatherapithunder.repo.local.model.WeatherResponse
import com.learning.weatherapithunder.repo.remote.EndPoints
import com.learning.weatherapithunder.repo.remote.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class WeatherActivityViewModel : ViewModel() {

    val cityWeather = MutableLiveData<WeatherResponse>()



    fun updateWeather() {
        val file = File("")
        var disposable = Networking.create(EndPoints.baseUrl, file, 2000)
            .queryWeather(cityWeather.value?.cityName ?: "London")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.updateLastFetchedTime()
                cityWeather.postValue(it)
            }, {
                Log.e("Error : ", "$it")
            })
    }
}