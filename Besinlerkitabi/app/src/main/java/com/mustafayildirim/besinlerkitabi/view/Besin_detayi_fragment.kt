package com.mustafayildirim.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mustafayildirim.besinlerkitabi.R
import com.mustafayildirim.besinlerkitabi.databinding.FragmentBesinDetayiFragmentBinding
import com.mustafayildirim.besinlerkitabi.util.gorselIndir
import com.mustafayildirim.besinlerkitabi.util.placeHolderYap
import com.mustafayildirim.besinlerkitabi.viewmodel.BesinDetayiViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_besin_detayi_fragment.*

class Besin_detayi_fragment : Fragment() {
    private lateinit var viewModel: BesinDetayiViewModel

private var besinId=0
    private lateinit var databinding:FragmentBesinDetayiFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding=DataBindingUtil.inflate(inflater,R.layout.fragment_besin_detayi_fragment,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            besinId=
                Besin_detayi_fragmentArgs.fromBundle(it).besinId

        }
        viewModel= ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomverisiniAl(besinId)
        ObserveLiveData()
    }
    fun ObserveLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin->
            besin?.let {

                databinding.secilenBesin=it
                
                /*
                besinIsmi.text=it.besinIsim
                besinKalorisi.text=it.besinKalori
                besinKarbonhidrat.text=it.besinKarbonhidrat
                besinProtein.text=it.besinProtein
                besinYag.text=it.besinYag
                context?.let {
                    besinImageView.gorselIndir(besin.besinGorsel, placeHolderYap(it))
                }


                 */

            }
        })
    }
}