package com.example.bnicasefirst.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bnicasefirst.R
import com.example.bnicasefirst.model.TransactionModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class TransactionAdapter(var context: Context) : RecyclerView.Adapter<TransactionAdapter.PromoHolder>() {
    var list: ArrayList<TransactionModel> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    fun setPost(list: ArrayList<TransactionModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoHolder {
        return PromoHolder(
            LayoutInflater.from(context).inflate(R.layout.item_transaction_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PromoHolder, position: Int) {
        var modelList:TransactionModel = list[position]
        holder.txtIdTranscation!!.text = modelList.transactionId
        holder.txtMerchantName!!.text = modelList.merchantName
        holder.txtBank!!.text = modelList.bank
        holder.txtNominal!!.text = modelList.nominal.toString().toCurrencyFormat()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PromoHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtIdTranscation: TextView = itemView!!.findViewById(R.id.txtIdTransaction)
        var txtMerchantName: TextView? = itemView!!.findViewById(R.id.txtMerchantName)
        var txtNominal: TextView? = itemView!!.findViewById(R.id.txtNominal)
        var txtBank: TextView? = itemView!!.findViewById(R.id.txtBank)

    }

    fun String.toCurrencyFormat(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDoubleOrNull() ?: return this
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }
}