package com.example.imagesearch.listener

import com.example.imagesearch.data.Document

interface ImageClickListener {
    fun onClickImage(document: Document, position: Int)
}