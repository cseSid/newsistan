package com.sid.newsistan.room

import androidx.lifecycle.LiveData

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import androidx.room.Update
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.dataClass.News


@androidx.room.Dao
interface Dao {

    // below method is use to
    // add data to database.
    @Insert
    fun insert(news: Articles?):Long


    // below line is use to delete a
    // specific course in our database.
    @Delete
    fun delete(news: Articles?) : Int


    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM news_table")
    fun getAllNews(): List<Articles>




}