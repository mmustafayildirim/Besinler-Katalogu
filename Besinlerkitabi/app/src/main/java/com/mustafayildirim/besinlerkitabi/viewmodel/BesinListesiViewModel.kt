package com.mustafayildirim.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafayildirim.besinlerkitabi.model.Besin
import com.mustafayildirim.besinlerkitabi.servicepackage.BesinApiService
import com.mustafayildirim.besinlerkitabi.servicepackage.besinDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application):BaseViewModel(application) {
    val besinler=MutableLiveData<List<Besin>>()
    val besinHataMesaji=MutableLiveData<Boolean>()
    val besinYukleniyor=MutableLiveData<Boolean>()
    private var guncellemeZamani=10*60*1000*1000*1000L

    private val besinApiService=BesinApiService()
    private val disposable=CompositeDisposable()
    private val ozelSheradPrefencens=com.mustafayildirim.besinlerkitabi.util.ozelSheradPrefencens(getApplication())

    fun refreshData(){
    val kaydedilmeZamani=ozelSheradPrefencens.zamaniAl()
    if(kaydedilmeZamani!=null&&kaydedilmeZamani!=0L&&System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
        //sqlcek
        verileriSqllitedanAl()
    }
        else{
        verileriInternettenAl()
        }
    }
    fun refreshFromInternet(){
        verileriInternettenAl()
    }
    private fun verileriSqllitedanAl(){
        besinYukleniyor.value=true
        launch {
           val besinListesi= besinDataBase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(), "ResimleriRoomdanAldık", Toast.LENGTH_LONG).show()
        }
    }
    private fun verileriInternettenAl(){
    besinYukleniyor.value=true
        disposable.add(
            besinApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Besin>>(){
                    override fun onSuccess(t: List<Besin>) {
                        //başarılı olursa işlem(liste Verdiyse)
                        sqliteSakla(t)
                        Toast.makeText(getApplication(), "ResimleriİnternettenAldık", Toast.LENGTH_LONG).show()
                    }


                    override fun onError(e: Throwable) {
                        //hata
                        besinHataMesaji.value=true
                        besinYukleniyor.value=false
                        e.printStackTrace()
                    }

                })
        )
    }
    private fun besinleriGoster(besinlerListelesi:List<Besin>){
        besinler.value=besinlerListelesi
        besinHataMesaji.value=false
        besinYukleniyor.value=false
    }
    private fun sqliteSakla(besinListesi:List<Besin>){
        launch {
            val dao=besinDataBase(getApplication()).besinDao()
            dao.deleteAllBesin()
           val uuidListesi= dao.insertAll(*besinListesi.toTypedArray())
            var i=0
            while (i<besinListesi.size){
                besinListesi[i].uuid=uuidListesi[i].toInt()
                i=i+1
            }
            besinleriGoster(besinListesi)
        }
        ozelSheradPrefencens.zamaniKaydet(System.nanoTime())
    }
}