package com.example.imagesearch.data

import com.google.gson.annotations.SerializedName

data class Document(
    val collection: String,
    @SerializedName("datetime")
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val image_url: String,
    @SerializedName("thumbnail_url")
    val thumbnail_url: String,
    val width: Int
)