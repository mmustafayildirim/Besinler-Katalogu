package com.mustafayildirim.besinlerkitabi.viewmodel

import android.app.Application
import android.util.MutableDouble
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafayildirim.besinlerkitabi.model.Besin
import com.mustafayildirim.besinlerkitabi.servicepackage.besinDataBase
import kotlinx.coroutines.launch
import java.util.*

class BesinDetayiViewModel(application: Application):BaseViewModel(application) {
    val besinLiveData= MutableLiveData<Besin>()
    fun roomverisiniAl( uuıd: Int){launch {
        val dao=besinDataBase(getApplication()).besinDao()
        val besin =dao.getBesin(uuıd)
        besinLiveData.value=besin
    }



    }

}