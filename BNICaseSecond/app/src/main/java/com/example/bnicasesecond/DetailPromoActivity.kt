package com.example.bnicasesecond

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bnicasesecond.databinding.ActivityDetailPromoBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DetailPromoActivity : AppCompatActivity(), OnMapReadyCallback, OnMapClickListener {
    private lateinit var binding : ActivityDetailPromoBinding
    private var mMap: GoogleMap? = null
    var latitude=0.0
    var longitude=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Detail Promo"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        var mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapLokasi) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        binding.namaPromo.text = intent.getStringExtra("nama")
        binding.jumlahPromo.text = intent.getStringExtra("jumlah")
        binding.deskripsiPromo.text = intent.getStringExtra("deskripsi")
        binding.lokasi.text = intent.getStringExtra("lokasi")
        Glide.with(this@DetailPromoActivity).load(intent.getStringExtra("gambar")).into(binding.imgPromo)
    }

    override fun onMapReady(location: GoogleMap?) {
        mMap = location
         latitude = intent.getStringExtra("latitude")!!.toDouble()
         longitude = intent.getStringExtra("longitude")!!.toDouble()
        val koordinat = LatLng(latitude,longitude)
        mMap!!.addMarker(MarkerOptions().position(koordinat).title(intent.getStringExtra("lokasi")))
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(koordinat,11.05f))
    }

    override fun onMapClick(p0: LatLng?) {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}