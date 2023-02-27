package com.sid.newsistan.apiService

import com.sid.newsistan.dataClass.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsService {

    companion object{
        val BASE_URL : String = "https://newsapi.org/"
    }

    @GET("v2/top-headlines")
    fun listOfNewsCategory(@Query("category") category : String,@Query("country") country:String): Call<News>

    @GET("v2/top-headlines")
    fun listOfNews(@Query("country") country:String): Call<News>

}