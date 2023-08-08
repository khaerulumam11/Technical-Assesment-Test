package com.example.bnicasefirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnicasefirst.adapter.TransactionAdapter
import com.example.bnicasefirst.databinding.ActivityMainBinding
import com.example.bnicasefirst.helper.database
import com.example.bnicasefirst.model.TransactionModel
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var listData: ArrayList<TransactionModel>? = ArrayList()
    var adapter:TransactionAdapter ?=null
    var saldo = 1000000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Pembayaran QRIS"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))

        binding.scanQR.setOnClickListener {
            var pindah = Intent(this@MainActivity, QRScannerActivity::class.java)
            startActivity(pindah)
        }

        adapter = TransactionAdapter(this@MainActivity)
        getDataList()

        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun getDataList() {
        listData!!.clear()
        database.use {
            val result = select(TransactionModel.TABLE_TRANSCATION)
            listData = result.parseList(classParser<TransactionModel>()) as ArrayList<TransactionModel>

            println("Data Trasaction "+listData.toString())
            println("Jumlah Trasaction "+listData!!.size)
        }
        adapter!!.setPost(listData!!)
        binding.rvList.adapter = adapter
        saldo = 1000000
        if(listData!!.isNotEmpty()) {
            for (i in 0 until listData!!.size) {
                saldo -= listData!![i].nominal.toInt()
            }
            binding.saldo.text = saldo.toString().toCurrencyFormat()
        } else {
            binding.saldo.text = saldo.toString().toCurrencyFormat()
        }
    }

    override fun onResume() {
        super.onResume()
        getDataList()
        adapter!!.notifyDataSetChanged()
        binding.rvList.adapter = adapter
    }

    fun String.toCurrencyFormat(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDoubleOrNull() ?: return this
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }

}