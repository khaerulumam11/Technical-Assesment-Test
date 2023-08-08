package com.example.bnicasefirst

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.bnicasefirst.databinding.ActivityQrscannerBinding

class QRScannerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQrscannerBinding
    private lateinit var scannerCode: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "QRIS"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.black))
        binding.toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_baseline_arrow_back_black_24))
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupPermissions()
        codeScanner()
    }

    override fun onResume() {
        super.onResume()
        scannerCode.startPreview()
    }

    override fun onPause() {
        scannerCode.releaseResources()
        super.onPause()
    }

    private fun codeScanner() {
        scannerCode = CodeScanner(this, binding.qrScan)

        scannerCode.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    var pindah = Intent(this@QRScannerActivity, PaymentActivity::class.java)
                    pindah.putExtra("code",it.text)
                    startActivity(pindah)
//                    Toast.makeText(this@QRScannerActivity,it.text, Toast.LENGTH_SHORT).show()
                }

            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    println("codeScanner: ${it.message}")
                }
            }

            binding.qrScan.setOnClickListener {
                scannerCode.startPreview()
            }

        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQ
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQ -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "You need the camera permission to use this app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val CAMERA_REQ = 101
    }
}