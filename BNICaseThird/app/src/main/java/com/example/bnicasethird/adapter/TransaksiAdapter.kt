package com.example.bnicasethird.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bnicasethird.R
import com.example.bnicasethird.model.DataModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class TransaksiAdapter(var context: Context) : RecyclerView.Adapter<TransaksiAdapter.PromoHolder>() {
    var list: ArrayList<DataModel.DataEntity> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    fun setPost(list: ArrayList<DataModel.DataEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoHolder {
        return PromoHolder(
            LayoutInflater.from(context).inflate(R.layout.item_transaksi_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PromoHolder, position: Int) {
        var modelList:DataModel.DataEntity = list[position]
        holder.txtTanggal!!.text = modelList.trxDate
        holder.txtNominal!!.text = modelList.nominal.toString().toCurrencyFormat()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PromoHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtTanggal: TextView = itemView!!.findViewById(R.id.txtTanggal)
        var txtNominal: TextView? = itemView!!.findViewById(R.id.txtNominal)

    }

    fun String.toCurrencyFormat(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDoubleOrNull() ?: return this
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }
}