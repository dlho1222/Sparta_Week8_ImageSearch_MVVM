package com.example.imagesearch.data

import com.google.gson.annotations.SerializedName

data class Document(
    val collection: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("display_sitename")
    val displaySiteName: String,
    val doc_url: String,
    val height: Int,
    val image_url: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val width: Int,
    var isLike: Boolean = false
)