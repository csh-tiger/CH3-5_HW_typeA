package com.example.ch3_5hw_api_typea

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("v2/search/image.json")

    //비동기일때는 무조건 suspend 붙여야 함.

    //비동기
    suspend fun getData(
        @Header("Authorization") apiKey:String?,
        @Query("query") query:String?,
        @Query("sort") sort:String?,
        @Query("page") page:Int,
        @Query("size") size:Int,
    ):Data?

    //동기
//    fun getData(
//        @Header("Authorization") apiKey:String?,
//        @Query("query") query:String?,
//        @Query("sort") sort:String?,
//        @Query("page") page:Int,
//        @Query("size") size:Int,
//    ):Call<Data>
}