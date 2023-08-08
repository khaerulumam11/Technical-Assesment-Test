package com.example.bnicasethird.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {
    @Expose
    @SerializedName("data")
    var data: List<DataEntity>? = null

    @Expose
    @SerializedName("type")
    var type: String? = null

    class Data {
        @Expose
        @SerializedName("data")
        var data: List<DataEntity>? = null

        @Expose
        @SerializedName("percentage")
        var percentage: String? = null

        @Expose
        @SerializedName("label")
        var label: String? = null
    }

    class DataEntity {
        @Expose
        @SerializedName("nominal")
        var nominal = 0

        @Expose
        @SerializedName("trx_date")
        var trxDate: String? = null
    }
}