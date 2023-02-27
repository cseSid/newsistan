package com.sid.newsistan.repository

import android.util.Log
import com.sid.newsistan.apiService.RetrofitClient
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.dataClass.News
import com.sid.newsistan.engine.Event
import com.sid.newsistan.engine.GlobalBus
import com.sid.newsistan.room.RoomDB
import com.sid.newsistan.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {

    val database = RoomDB.getSingleInstance()


    fun getNewsDataFromDb(){
        val dao = database?.Dao()
        val news = dao?.getAllNews()
        val event = Event.Companion.RepoToViewModelAllFromDb(news)
        GlobalBus.sbus?.post(event)
    }


    fun saveNewsLocally(newss : Articles){
        val dao = database?.Dao()
        val news = dao?.insert(newss)
        val event = Event.Companion.RepoToViewModelInsertSucessfull(news!!)
        GlobalBus.sbus?.post(event)
    }


    fun deleteDataLocally(newss : Articles){
        val dao = database?.Dao()
        val news = dao?.delete(newss)
        val event = Event.Companion.RepoToViewModelDeleteSucessfull(news!!)
        GlobalBus.sbus?.post(event)
    }


    fun getNewsData() {
    val call: Call<News> = RetrofitClient.getInstance()
      .myApi
      .listOfNews(Utils.INDIA)
    call.enqueue(object : Callback<News?> {
      override fun onResponse(call: Call<News?>, response: Response<News?>) {
          val newsData: News = response.body()!!

         val event = Event.Companion.RepoToViewModelAll(newsData)
          GlobalBus.sbus?.post(event)
      }

      override fun onFailure(call: Call<News?>, t: Throwable) {
          Log.d("Repo--", "onFailure: ${t.message}")

          val errorEvent = t.message?.let { Event.Companion.ErrorHandler(it) }
          GlobalBus.sbus?.post(errorEvent)

      }
    })
  }




    fun getNewsData(category: String) {
        val call: Call<News> = RetrofitClient.getInstance()
            .myApi
            .listOfNewsCategory(category,Utils.INDIA)
        call.enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                val newsData: News = response.body()!!

                val event = Event.Companion.RepoToViewModelAll(newsData)
                GlobalBus.sbus?.post(event)
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                Log.d("Repo--", "onFailure: ${t.message}")

                val errorEvent = t.message?.let { Event.Companion.ErrorHandler(it) }
                GlobalBus.sbus?.post(errorEvent)

            }
        })
    }





}