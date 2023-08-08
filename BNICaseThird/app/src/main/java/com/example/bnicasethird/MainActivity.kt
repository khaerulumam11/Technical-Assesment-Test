package com.example.bnicasethird

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bnicasethird.databinding.ActivityMainBinding
import com.example.bnicasethird.model.DataModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import org.json.JSONArray
import java.util.*


class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var dataPieList = ArrayList<DataModel.Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Data Transaksi"
        binding.toolbar.setTitleTextColor(resources.getColor(com.example.bnicasethird.R.color.white))

        val jsonRawData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "response",
                "raw",
                applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val outputData = JSONArray(jsonRawData)
        println("Data Output $outputData")
        var dataLine = outputData.getJSONObject(1).getJSONObject("data").getJSONArray("month")
        val linevalues = ArrayList<Entry>()
        var xValues = 10f
        for (i in 0 until dataLine.length()){
            xValues+=10f
            linevalues.add(Entry(xValues, dataLine[i].toString().toFloat()))
        }

        val linedataset = LineDataSet(linevalues, "Data")
        linedataset.color = resources.getColor(R.color.holo_purple)

        linedataset.circleRadius = 5f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 10F
        linedataset.fillColor = resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)

        val data = LineData(linedataset)
        binding.chart1.data = data
        binding.chart1.setBackgroundColor(resources.getColor(R.color.white))
        binding.chart1.animateXY(2000, 2000, Easing.EaseInCubic)

        binding.chartPie.setUsePercentValues(true)
        binding.chartPie.getDescription().setEnabled(false)
        binding.chartPie.setExtraOffsets(5f, 10f, 5f, 5f)

        binding.chartPie.setDragDecelerationFrictionCoef(0.95f)
//        binding.chartPie.setCenterText(generateCenterSpannableText())

        binding.chartPie.setDrawHoleEnabled(true)
        binding.chartPie.setHoleColor(Color.WHITE)

        binding.chartPie.setTransparentCircleColor(Color.WHITE)
        binding.chartPie.setTransparentCircleAlpha(110)

        binding.chartPie.setHoleRadius(58f)
        binding.chartPie.setTransparentCircleRadius(61f)

        binding.chartPie.setDrawCenterText(true)

        binding.chartPie.setRotationAngle(0f)
        binding.chartPie.setRotationEnabled(true)
        binding.chartPie.setHighlightPerTapEnabled(true)
        binding.chartPie.setOnChartValueSelectedListener(this)
        binding.chartPie.animateY(1400, Easing.EaseInOutQuad)

        binding.chartPie.setEntryLabelColor(Color.WHITE)
        binding.chartPie.setEntryLabelTextSize(12f)

        dataPieList.clear()
        var dataPie = outputData.getJSONObject(0).getJSONArray("data")
        for (a in 0 until dataPie.length()){
            var model = DataModel.Data()
            var listData :ArrayList<DataModel.DataEntity> = ArrayList()
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
        val entries: ArrayList<PieEntry> = ArrayList()
        var xValue =  1f
        for (x in 0 until dataPieList.size) {
            xValue +=5f
            entries.add(
                PieEntry(
                    dataPieList[x].percentage!!.toFloat(),
                    dataPieList[x].label
                )
            )
        }
        val dataSet = PieDataSet(entries, "Data Transaksi")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors: ArrayList<Int> = ArrayList()
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(11f)
        pieData.setValueTextColor(Color.WHITE)
        binding.chartPie.setData(pieData)
        binding.chartPie.setOnChartValueSelectedListener(this)
        // undo all highlights
        binding.chartPie.highlightValues(null)
        binding.chartPie.invalidate()
    }


    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null)
            return;

        var pindah = Intent(this@MainActivity, DetailTransaksiActivity::class.java)
        pindah.putExtra("position", h!!.x )
        startActivity(pindah)

        println(
            "Value: " + e.getY() + ", index: " + h!!.getX()
                    + ", DataSet index: " + h!!.getDataSetIndex());
    }

    override fun onNothingSelected() {

    }


}