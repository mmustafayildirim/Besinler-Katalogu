package com.mustafayildirim.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mustafayildirim.besinlerkitabi.R
import com.mustafayildirim.besinlerkitabi.databinding.BesinRecycleRowBinding
import com.mustafayildirim.besinlerkitabi.model.Besin
import com.mustafayildirim.besinlerkitabi.util.gorselIndir
import com.mustafayildirim.besinlerkitabi.util.placeHolderYap
import com.mustafayildirim.besinlerkitabi.view.Besin_listesi_fragmentDirections
import kotlinx.android.synthetic.main.besin_recycle_row.view.*

class besin_recycle_adapter(val besinListesi:ArrayList<Besin>):RecyclerView.Adapter<besin_recycle_adapter.besinViewHolder>(),BesinClickListener {
    class besinViewHolder(var view:BesinRecycleRowBinding):RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): besinViewHolder {
        val infilater=LayoutInflater.from(parent.context)
        //val view=infilater.inflate(R.layout.besin_recycle_row,parent,false)
        val view=DataBindingUtil.inflate<BesinRecycleRowBinding>(infilater,R.layout.besin_recycle_row,parent,false)
        return besinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    override fun onBindViewHolder(holder: besinViewHolder, position: Int) {

        holder.view.besin=besinListesi[position]
        holder.view.listener=this
    }
       /* holder.itemView.isim.text=besinListesi.get(position).besinIsim
        holder.itemView.kalori.text=besinListesi.get(position).besinKalori
        holder.itemView.setOnClickListener {
            val action=Besin_listesi_fragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)

        }
        holder.itemView.ImageView.gorselIndir(besinListesi.get(position).besinGorsel, placeHolderYap(holder.itemView.context))
    }

        */
        fun besinListesiniGuncelle(yeniBesinListesi:List<Besin>){
            besinListesi.clear()
            besinListesi.addAll(yeniBesinListesi)
            notifyDataSetChanged()

        }

    override fun besinTiklandi(view: View) {
        val uuid=view.besin_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action=Besin_listesi_fragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }

}