package com.example.imagesearch.retrofit

import com.example.imagesearch.data.ImageSearch
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val AUTH_HEADER = "KakaoAK 32cf9215aec6834ed31e301f93ba9df6"
private const val SORT_DEFAULT = "accuracy"
private const val PAGE_NUMBER = 1
private const val API_MAX_RESULT = 80
interface ImageSearchApi {
    @GET("/v2/search/image")
    suspend fun getImage(
        @Header("Authorization") authorization: String = AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String = SORT_DEFAULT,
        @Query("page") page: Int = PAGE_NUMBER,
        @Query("size") size: Int = API_MAX_RESULT
    ): ImageSearch
}