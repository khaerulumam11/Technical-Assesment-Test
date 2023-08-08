package com.example.bnicasethird

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnicasethird.adapter.TransaksiAdapter
import com.example.bnicasethird.databinding.ActivityDetailTransaksiBinding
import com.example.bnicasethird.model.DataModel
import org.json.JSONArray
import java.util.ArrayList

class DetailTransaksiActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailTransaksiBinding
    private var dataPieList = ArrayList<DataModel.Data>()
    private var adapter :TransaksiAdapter?=null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Detail Transaksi"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_white_24)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        adapter = TransaksiAdapter(this)

        val jsonRawData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "response",
                "raw",
                applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val outputData = JSONArray(jsonRawData)
        dataPieList.clear()
        var dataPie = outputData.getJSONObject(0).getJSONArray("data")
        for (a in 0 until dataPie.length()){
            var model = DataModel.Data()
            var listData : ArrayList<DataModel.DataEntity> = ArrayList()
            model.label = dataPie.getJSONObject(a).getString("label")
            model.percentage = dataPie.getJSONObject(a).getString("percentage")
            for(b in 0 until dataPie.getJSONObject(a).getJSONArray("data")!!.length()){
                var modelEntity = DataModel.DataEntity()
                modelEntity.nominal = dataPie.getJSONObject(a).getJSONArray("data").getJSONObject(b).getString("nominal").toInt()
                modelEntity.trxDate = dataPie.getJSONObject(a).getJSONArray("data").getJSONObject(b).getString("trx_date")
                listData.add(modelEntity)
            }
            model.data = listData
            dataPieList.add(model)
        }

        for(x in 0 until dataPieList.size){
            if (intent.getFloatExtra("position",0f).toInt() == x){
                binding.label.text = dataPieList[x].label
                binding.percentage.text = "${dataPieList[x].percentage}%"
                adapter!!.setPost(dataPieList[x].data as ArrayList)
            }
        }
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

    }
}