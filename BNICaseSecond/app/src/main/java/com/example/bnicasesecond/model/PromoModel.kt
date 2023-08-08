package com.example.bnicasesecond.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PromoModel {

    @Expose
    @SerializedName("img")
    var img: ImgEntity? = null

    @Expose
    @SerializedName("count")
    var count = 0

    @Expose
    @SerializedName("lokasi")
    var lokasi: String? = null

    @Expose
    @SerializedName("createdAt")
    var createdat: String? = null

    @Expose
    @SerializedName("alt")
    var alt: String? = null

    @Expose
    @SerializedName("longitude")
    var longitude: String? = null

    @Expose
    @SerializedName("latitude")
    var latitude: String? = null

    @Expose
    @SerializedName("desc")
    var desc: String? = null

    @Expose
    @SerializedName("nama")
    var nama: String? = null

    @Expose
    @SerializedName("updated_at")
    var updatedAt: String? = null

    @Expose
    @SerializedName("created_at")
    var createdAt: String? = null

    @Expose
    @SerializedName("published_at")
    var publishedAt: String? = null

    @Expose
    @SerializedName("id")
    var id = 0

    class ThumbnailEntity {
        @Expose
        @SerializedName("height")
        var height = 0

        @Expose
        @SerializedName("width")
        var width = 0

        @Expose
        @SerializedName("size")
        var size = 0.0

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("mime")
        var mime: String? = null

        @Expose
        @SerializedName("hash")
        var hash: String? = null

        @Expose
        @SerializedName("url")
        var url: String? = null

        @Expose
        @SerializedName("ext")
        var ext: String? = null
    }

    class FormatsEntity {
        @Expose
        @SerializedName("thumbnail")
        var thumbnail: ThumbnailEntity? = null

        @Expose
        @SerializedName("medium")
        var medium: MediumEntity? = null

        @Expose
        @SerializedName("small")
        var small: SmallEntity? = null
    }

    class ImgEntity {
        @Expose
        @SerializedName("updated_at")
        var updatedAt: String? = null

        @Expose
        @SerializedName("created_at")
        var createdAt: String? = null

        @Expose
        @SerializedName("provider")
        var provider: String? = null

        @Expose
        @SerializedName("url")
        var url: String? = null

        @Expose
        @SerializedName("size")
        var size = 0.0

        @Expose
        @SerializedName("mime")
        var mime: String? = null

        @Expose
        @SerializedName("ext")
        var ext: String? = null

        @Expose
        @SerializedName("hash")
        var hash: String? = null

        @Expose
        @SerializedName("formats")
        var formats: FormatsEntity? = null

        @Expose
        @SerializedName("height")
        var height = 0

        @Expose
        @SerializedName("width")
        var width = 0

        @Expose
        @SerializedName("caption")
        var caption: String? = null

        @Expose
        @SerializedName("alternativeText")
        var alternativetext: String? = null

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("id")
        var id = 0
    }

    class MediumEntity {
        @Expose
        @SerializedName("height")
        var height = 0

        @Expose
        @SerializedName("width")
        var width = 0

        @Expose
        @SerializedName("size")
        var size = 0.0

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("mime")
        var mime: String? = null

        @Expose
        @SerializedName("hash")
        var hash: String? = null

        @Expose
        @SerializedName("url")
        var url: String? = null

        @Expose
        @SerializedName("ext")
        var ext: String? = null
    }

    class SmallEntity {
        @Expose
        @SerializedName("height")
        var height = 0

        @Expose
        @SerializedName("width")
        var width = 0

        @Expose
        @SerializedName("size")
        var size = 0.0

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("mime")
        var mime: String? = null

        @Expose
        @SerializedName("hash")
        var hash: String? = null

        @Expose
        @SerializedName("url")
        var url: String? = null

        @Expose
        @SerializedName("ext")
        var ext: String? = null


    }
}