package com.example.ch3_5hw_api_typea

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {
    private const val DUST_BASE_URL = "https://dapi.kakao.com/"


    private fun createOkHttpClient(): OkHttpClient {

        //통신에 있어 어떤 통신을 하고 있는지 가로채서 로그를 찍어줌
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

//        if (BuildConfig.DEBUG)
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        else
//            interceptor.level = HttpLoggingInterceptor.Level.NONE

        //서버랑 통신하는 과정에서 20초동안 응답이 없으면 타임아웃
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val dustRetrofit = Retrofit.Builder()
        .baseUrl(DUST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    val dustNetWork: NetWorkInterface = dustRetrofit.create(NetWorkInterface::class.java)
}