package com.example.bnicasesecond.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnicasesecond.DetailPromoActivity
import com.example.bnicasesecond.R
import com.example.bnicasesecond.model.PromoModel

class PromoAdapter(var context: Context) : RecyclerView.Adapter<PromoAdapter.PromoHolder>() {
    var list: ArrayList<PromoModel> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    fun setPost(list: ArrayList<PromoModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoHolder {
        return PromoHolder(
            LayoutInflater.from(context).inflate(R.layout.item_promo_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PromoHolder, position: Int) {
        var modelList:PromoModel = list[position]
        holder.txtName!!.text = modelList.nama
        holder.txtLokasi!!.text = modelList.lokasi
        holder.txtJumlah!!.text = "Promo tersisa : ${modelList.count}"
        Glide.with(context).load(modelList.img!!.url).into(holder.gambar!!)

        holder.itemView.setOnClickListener {
            var pindah = Intent(context,DetailPromoActivity::class.java )
            pindah.putExtra("nama", modelList.nama)
            pindah.putExtra("jumlah", modelList.count.toString())
            pindah.putExtra("deskripsi", modelList.desc)
            pindah.putExtra("lokasi", modelList.lokasi)
            pindah.putExtra("gambar", modelList.img!!.url)
            pindah.putExtra("latitude", modelList.latitude)
            pindah.putExtra("longitude", modelList.longitude)
            context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PromoHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtName: TextView = itemView!!.findViewById(R.id.txtNama)
        var txtLokasi: TextView? = itemView!!.findViewById(R.id.txtLokasi)
        var txtJumlah: TextView? = itemView!!.findViewById(R.id.txtJumlah)
        var gambar: ImageView? = itemView!!.findViewById(R.id.img)

    }
}