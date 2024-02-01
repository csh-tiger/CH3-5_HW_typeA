package com.example.ch3_5hw_api_typea

import com.google.gson.annotations.SerializedName

data class Data(
    val meta: DataMeta,
    val documents: MutableList<DataItem>?
)

data class DataMeta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount:Int,
    @SerializedName("is_end")
    val isEnd:Boolean
)

data class DataItem(
    val collection: String,    //분류(news, )
    @SerializedName("thumbnail_url")
    val thumbnail: String,    //썸네일 url
    @SerializedName("image_url")
    val image: String,    //이미지 url
    val width: Int,    //이미지 가로
    val height: Int,    //이미지 세로
    @SerializedName("display_sitename")
    val siteName: String,    //출처
    @SerializedName("doc_url")
    val docUrl: String,    //본문 url
    val datetime: String    //작성시간
)


//data class DataResponse(
//    @SerializedName("body")
//    val dataBody: DataBody,
//    @SerializedName("header")
//    val dataHeader: DataHeader
//)

data class DataBody(
    val totalCount: Int,
    @SerializedName("items")
    val dataItem: MutableList<DataItem>?,
    val pageNo: Int,
    val numOfRows: Int
)

data class DataHeader(
    val resultCode: String,
    val resultMsg: String
)

//data class DataItem(
//    val so2Grade: String,
//    val coFlag: String?,
//    val khaiValue: String,
//    val so2Value: String,
//    val coValue: String,
//    val pm25Flag: String?,
//    val pm10Flag: String?,
//    val o3Grade: String,
//    val pm10Value: String,
//    val khaiGrade: String,
//    val pm25Value: String,
//    val sidoName: String,
//    val no2Flag: String?,
//    val no2Grade: String,
//    val o3Flag: String?,
//    val pm25Grade: String,
//    val so2Flag: String?,
//    val dataTime: String,
//    val coGrade: String,
//    val no2Value: String,
//    val stationName: String,
//    val pm10Grade: String,
//    val o3Value: String
//)