package com.example.imagesearch.manager

import com.example.imagesearch.data.Document

object DocumentsManager {
    private val items by lazy { mutableListOf<Document>() }

    fun addDocument(document: MutableList<Document>) {
        items.addAll(document)
    }

    fun getDocument(): MutableList<Document> {
        return items
    }

    //좋아요 토글
    fun toggleLike(document: Document) {
        document.isLike = !document.isLike
    }

    //좋아요 눌린 이미지 반환
    fun getLikeContents(): MutableList<Document> {
        return items.filter { it.isLike }.toMutableList()
    }
}