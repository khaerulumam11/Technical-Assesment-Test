package com.example.bnicasesecond

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.example.bnicasesecond.adapter.PromoAdapter
import com.example.bnicasesecond.databinding.ActivityMainBinding
import com.example.bnicasesecond.model.PromoModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var adapter: PromoAdapter ?= null
    private var listData = ArrayList<PromoModel>()
    private var gson: Gson? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var gsonBuilder = GsonBuilder().serializeNulls()
        gson = gsonBuilder.create()
        binding.toolbar.title = "Daftar Promo BNI"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.addressLookingUp.show()
        adapter = PromoAdapter(this)
        getData()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

    }

    private fun getData() {
        listData.clear()
        AndroidNetworking.get("https://content.digi46.id/promos")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    println("Response $response")
                    for(i in 0 until response.length()) {
                        var courseRespMdl: PromoModel = gson!!.fromJson(
                            response.getJSONObject(i).toString(),
                            PromoModel::class.java
                        )
                        listData.add(courseRespMdl)
                    }
                    println("Jumlah array" +listData.size)
                    adapter?.setPost(listData)
                    binding!!.addressLookingUp.hide()
                }

                override fun onError(anError: ANError) {
                    println("Erorr " + anError.message)
                }
            })
    }
}