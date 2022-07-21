package com.example.kotlinlectureone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlinlectureone.model.Country
import com.example.kotlinlectureone.service.Connection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val connection = Connection()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun getDataFromApi() {
        countryLoading.value = true

        disposable.add(
            connection.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        showCountries(t)
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }

                })


        )
    }

    private fun showCountries(t: List<Country>) {
        countries.value = t
        countryError.value = false
        countryLoading.value = false
    }


}