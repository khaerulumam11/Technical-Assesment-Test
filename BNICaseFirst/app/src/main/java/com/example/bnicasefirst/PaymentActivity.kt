package com.example.bnicasefirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bnicasefirst.databinding.ActivityPaymentBinding
import com.example.bnicasefirst.helper.database
import com.example.bnicasefirst.model.TransactionModel
import java.text.NumberFormat
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import java.util.*

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    var kodeQR = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kodeQR = intent.getStringExtra("code")!!
        binding.toolbar.title = "Detail Pembayaran"
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_baseline_arrow_back_white_24))
        binding.toolbar.setNavigationOnClickListener { finish() }

        var delimiter = "."
        var list = kodeQR.split(delimiter)

        binding.transaksiId.text = list[1]
        binding.merchantName.text = list[2]
        binding.bankSumber.text = list[0]
        binding.nominalTransaksi.text = list[3].toCurrencyFormat()


        binding.btnPay.setOnClickListener {
            database.use {
                insert(TransactionModel.TABLE_TRANSCATION,
                TransactionModel.TRANSACTION_ID to list[1],
                TransactionModel.MERCHANT_NAME to list[2],
                    TransactionModel.NOMINAL to list[3].toInt(),
                TransactionModel.BANK to list[0])
            }

            toast("Pembayaran Berhasil")
            var pindah = Intent(this@PaymentActivity, MainActivity::class.java)
            startActivity(pindah)
            finish()
        }
        println("Isi Data : $list")

    }

    fun String.toCurrencyFormat(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDoubleOrNull() ?: return this
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }
}