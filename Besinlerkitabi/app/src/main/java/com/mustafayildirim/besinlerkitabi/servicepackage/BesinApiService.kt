package com.mustafayildirim.besinlerkitabi.servicepackage

import com.mustafayildirim.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class BesinApiService
//https://github.com/mmustafayildirim/Besinler/blob/master/besinler.json
{
    private val BASE_URL="https://github.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Besinapi::class.java)

    fun getData():Single<List<Besin>>{
        return  api.getBesin()

    }
}