package com.mustafayildirim.besinlerkitabi.servicepackage

import com.mustafayildirim.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

interface Besinapi {
    @GET("mmustafayildirim/Besinler/blob/master/besinler.json")
    fun getBesin():Single<List<Besin>>
}