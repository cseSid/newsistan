package com.sid.newsistan.engine

import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.dataClass.News

class Event {
    companion object{
        class RepoToViewModelAll(val message: News)
        class RepoToViewModelAllFromDb(val message : List<Articles>?)
        class RepoToViewModelInsertSucessfull(val msg : Long)
        class RepoToViewModelDeleteSucessfull(val delete : Int)
        class RepoToViewModelCategory(val message: News)
        class ErrorHandler(val error : String)
    }
}