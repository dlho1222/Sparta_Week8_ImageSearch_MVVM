package com.example.imagesearch.retrofit

import com.example.imagesearch.data.ImageSearch
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val AUTH_HEADER = "KakaoAK 32cf9215aec6834ed31e301f93ba9df6"

interface ImageSearchApi {
    @GET("/v2/search/image")
    suspend fun getImage(
        @Header("Authorization") authorization: String = AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80
    ): ImageSearch
}