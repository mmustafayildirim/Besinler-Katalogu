package com.mustafayildirim.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayildirim.besinlerkitabi.R
import com.mustafayildirim.besinlerkitabi.adapter.besin_recycle_adapter
import com.mustafayildirim.besinlerkitabi.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi_fragment.*


class Besin_listesi_fragment : Fragment() {

private lateinit var viewModel:BesinListesiViewModel
    private val recycleBesinAdaptor=besin_recycle_adapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshData()
        besinRecyclerView.layoutManager=LinearLayoutManager(context)
        besinRecyclerView.adapter=recycleBesinAdaptor
        swipe_refresh.setOnRefreshListener {
            besinYukleniyor.visibility=View.VISIBLE
            hataMesaji.visibility=View.GONE
            besinRecyclerView.visibility=View.GONE
            viewModel.refreshFromInternet()
            swipe_refresh.isRefreshing=false
        }
        obserLiveData()



    }
    fun obserLiveData(){
        viewModel.besinler.observe( viewLifecycleOwner, Observer { besinler->
            besinler?.let {
                besinRecyclerView.visibility=View.VISIBLE
                recycleBesinAdaptor.besinListesiniGuncelle(besinler)

            }
        })
            viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata->
                hata?.let {
                    if (it){
                        hataMesaji.visibility=View.VISIBLE
                        besinRecyclerView.visibility=View.GONE
                    }
                    else{
                        hataMesaji.visibility=View.GONE
                    }
                }
            })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer {
            yukleniyor->
            yukleniyor?.let {
                if(it){
                    besinRecyclerView.visibility=View.GONE
                    hataMesaji.visibility=View.GONE
                    besinYukleniyor.visibility=View.VISIBLE
                }else{
                        besinYukleniyor.visibility=View.GONE
                }
            }
        })
    }

}