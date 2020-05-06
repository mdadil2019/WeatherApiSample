package com.learning.weatherapithunder.weather

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
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

    var cityWeather = ObservableField<WeatherResponse>()

    var cityName  = ObservableField<String>("London")

    fun updateWeather() {
        val file = File("")
        var disposable = Networking.create(EndPoints.baseUrl, file, 2000)
            .queryWeather(cityName.get().toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.updateLastFetchedTime()
                cityWeather.set(it)
            }, {
                Log.e("Error : ", "$it")
            })
    }
}