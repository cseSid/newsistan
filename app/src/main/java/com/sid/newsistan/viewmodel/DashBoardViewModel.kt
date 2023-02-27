package com.sid.newsistan.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.dataClass.News
import com.sid.newsistan.engine.Event
import com.sid.newsistan.engine.GlobalBus
import com.sid.newsistan.repository.Repository
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


class DashBoardViewModel : ViewModel() {

    var getAllNewsList : MutableLiveData<News> = MutableLiveData<News>()
    var getAllNewsListFromDb : MutableLiveData<List<Articles>?> = MutableLiveData<List<Articles>?>()
    var getNewsByCategoryList : MutableLiveData<News> = MutableLiveData<News>()
    var seccessfullInsert : MutableLiveData<Long> = MutableLiveData<Long>()
    var seccessfullDelete : MutableLiveData<Int> = MutableLiveData<Int>()
    var allFaliure : MutableLiveData<String> = MutableLiveData<String>()
    var categoryFaliure : MutableLiveData<String> = MutableLiveData<String>()
    private val backgroundExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private val repo  = Repository()

// Execute a task in the background thread.


    fun getAllNews(){
        backgroundExecutor.execute{
            repo.getNewsData()
        }
        if(!GlobalBus.sbus?.isRegistered(this)!!){
            GlobalBus.sbus?.register(this)
        }

    }

    fun getCategoryNewsList(category : String){
        backgroundExecutor.execute{
            repo.getNewsData(category)
        }
        if(!GlobalBus.sbus?.isRegistered(this)!!){
            GlobalBus.sbus?.register(this)
        }
    }

    fun getNewsDataFromDb(){
        backgroundExecutor.execute{
            repo.getNewsDataFromDb()
        }
        if(!GlobalBus.sbus?.isRegistered(this)!!){
            GlobalBus.sbus?.register(this)
        }
    }

    fun saveDataLocally(news : Articles){
        backgroundExecutor.execute{
            repo.saveNewsLocally(news)
        }
        if(!GlobalBus.sbus?.isRegistered(this)!!){
            GlobalBus.sbus?.register(this)
        }
    }


    fun deleteDataLocally(news : Articles){
        backgroundExecutor.execute{
            repo.deleteDataLocally(news)
        }
        if(!GlobalBus.sbus?.isRegistered(this)!!){
            GlobalBus.sbus?.register(this)
        }
    }





    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onInsertSucessfull(event : Event.Companion.RepoToViewModelInsertSucessfull){
        val value = event.msg
        seccessfullInsert.postValue(value)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDeleteSucessfull(event : Event.Companion.RepoToViewModelDeleteSucessfull){
        val value = event.delete
        seccessfullDelete.postValue(value)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewsListFromDb(event : Event.Companion.RepoToViewModelAllFromDb){
        val value = event.message
        getAllNewsListFromDb.postValue(value)
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewsListSuccess(event : Event.Companion.RepoToViewModelAll){
        val value = event.message
        getAllNewsList.postValue(value)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewListByCategory(event: Event.Companion.RepoToViewModelCategory){
        val value = event.message
        getNewsByCategoryList.postValue(value)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewListFaliure(event: Event.Companion.ErrorHandler){
        val value = event.error
        allFaliure.postValue(value)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewCategoryListFaliure(event: Event.Companion.ErrorHandler){
        val value = event.error
        categoryFaliure.postValue(value)
    }





}