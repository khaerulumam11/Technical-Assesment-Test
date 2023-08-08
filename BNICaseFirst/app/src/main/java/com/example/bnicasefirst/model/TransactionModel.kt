package com.example.bnicasefirst.model

import android.os.Parcel
import android.os.Parcelable

data class TransactionModel(
    val id : Int?,
    val transactionId:String,
    val merchantName:String,
    val nominal:String,
    val bank:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(transactionId)
        parcel.writeString(merchantName)
        parcel.writeString(nominal)
        parcel.writeString(bank)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TransactionModel> {
        const val TABLE_TRANSCATION = "tbl_transaction"
        const val ID = "id"
        const val TRANSACTION_ID = "transaction_id"
        const val MERCHANT_NAME = "merchant_name"
        const val NOMINAL = "nominal"
        const val BANK = "bank"
        override fun createFromParcel(parcel: Parcel): TransactionModel {
            return TransactionModel(parcel)
        }

        override fun newArray(size: Int): Array<TransactionModel?> {
            return arrayOfNulls(size)
        }
    }
}