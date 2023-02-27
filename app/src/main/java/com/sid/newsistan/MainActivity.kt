package com.sid.newsistan

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.databinding.ActivityDashBoardBinding
import com.sid.newsistan.engine.GlobalBus
import com.sid.newsistan.ui.fragments.Home
import com.sid.newsistan.viewmodel.DashBoardViewModel
import java.net.InetAddress


class MainActivity : AppCompatActivity() {

    private var list : ArrayList<Articles> = arrayListOf()
    lateinit var viewModel: DashBoardViewModel
    lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        binding.progressBar.visibility = View.VISIBLE
        setContentView(binding.root)
        supportActionBar?.title = "DashBoard"

        // view model instance
      viewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]

//        binding.scroll.visibility = View.VISIBLE
//        viewModel.getAllNews()

        if(internetIsConnected()){
            binding.scroll.visibility = View.VISIBLE
            viewModel.getAllNews()
        }else{
            binding.scroll.visibility = View.GONE
            viewModel.getNewsDataFromDb()
        }
        binding.allTab.setCardBackgroundColor(resources.getColor(R.color.white))
        binding.allTxt.setTextColor(resources.getColor(R.color.black))

        viewModel.getAllNewsList.observe(this, Observer {
                       list.clear()
            binding.progressBar.visibility = View.GONE
                       list = it.articles

            binding.fragmentContainer.visibility  = View.VISIBLE
            openFrag()

        })


        viewModel.getAllNewsListFromDb.observe(this, Observer {
            list.clear()
            binding.progressBar.visibility = View.GONE
            if (it != null){
                list = it as ArrayList<Articles>
            }else{
                binding.fragmentContainer.visibility  = View.GONE
                return@Observer
            }
            binding.fragmentContainer.visibility  = View.VISIBLE
            openFrag()
        })


        viewModel.getNewsByCategoryList.observe(this, Observer {
            list.clear()
            binding.progressBar.visibility = View.GONE
            list = it.articles
            binding.fragmentContainer.visibility  = View.VISIBLE
            openFrag()
        })

        viewModel.categoryFaliure.observe(this, Observer {
            list.clear()
            binding.progressBar.visibility = View.GONE
            binding.fragmentContainer.visibility  = View.GONE
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        viewModel.allFaliure.observe(this, Observer {
            list.clear()
            binding.progressBar.visibility = View.GONE
            binding.fragmentContainer.visibility  = View.GONE
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

       binding.allTab.setOnClickListener {
           binding.progressBar.visibility = View.VISIBLE
           binding.allTab.setCardBackgroundColor(resources.getColor(R.color.white))
           binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.black))
           binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.black))
           binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.black))
           binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.black))
           binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.black))
           binding.allTxt.setTextColor(resources.getColor(R.color.black))
           binding.businessTxt.setTextColor(resources.getColor(R.color.white))
           binding.entertainmentTxt.setTextColor(resources.getColor(R.color.white))
           binding.generalTxt.setTextColor(resources.getColor(R.color.white))
           binding.healthTxt.setTextColor(resources.getColor(R.color.white))
           binding.scienceTxt.setTextColor(resources.getColor(R.color.white))
           binding.fragmentContainer.visibility  = View.GONE
           viewModel.getAllNews()

       }

        binding.businessTab.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.allTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.allTxt.setTextColor(resources.getColor(R.color.white))
            binding.businessTxt.setTextColor(resources.getColor(R.color.black))
            binding.entertainmentTxt.setTextColor(resources.getColor(R.color.white))
            binding.generalTxt.setTextColor(resources.getColor(R.color.white))
            binding.healthTxt.setTextColor(resources.getColor(R.color.white))
            binding.scienceTxt.setTextColor(resources.getColor(R.color.white))
            binding.fragmentContainer.visibility  = View.GONE
           viewModel.getCategoryNewsList("business")


       }

        binding.entertainmentTab.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.allTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.allTxt.setTextColor(resources.getColor(R.color.white))
            binding.businessTxt.setTextColor(resources.getColor(R.color.white))
            binding.entertainmentTxt.setTextColor(resources.getColor(R.color.black))
            binding.generalTxt.setTextColor(resources.getColor(R.color.white))
            binding.healthTxt.setTextColor(resources.getColor(R.color.white))
            binding.scienceTxt.setTextColor(resources.getColor(R.color.white))
            binding.fragmentContainer.visibility  = View.GONE
           viewModel.getCategoryNewsList("entertainment")

       }

        binding.generalTab.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.allTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.black))

            binding.allTxt.setTextColor(resources.getColor(R.color.white))
            binding.businessTxt.setTextColor(resources.getColor(R.color.white))
            binding.entertainmentTxt.setTextColor(resources.getColor(R.color.white))
            binding.generalTxt.setTextColor(resources.getColor(R.color.black))
            binding.healthTxt.setTextColor(resources.getColor(R.color.white))
            binding.scienceTxt.setTextColor(resources.getColor(R.color.white))
            binding.fragmentContainer.visibility  = View.GONE
           viewModel.getCategoryNewsList("general")

       }

        binding.healthTab.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.allTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.black))

            binding.allTxt.setTextColor(resources.getColor(R.color.white))
            binding.businessTxt.setTextColor(resources.getColor(R.color.white))
            binding.entertainmentTxt.setTextColor(resources.getColor(R.color.white))
            binding.generalTxt.setTextColor(resources.getColor(R.color.white))
            binding.healthTxt.setTextColor(resources.getColor(R.color.black))
            binding.scienceTxt.setTextColor(resources.getColor(R.color.white))
            binding.fragmentContainer.visibility  = View.GONE
           viewModel.getCategoryNewsList("health")

       }

        binding.scienceTab.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.allTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.businessTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.entertainmentTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.generalTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.healthTab.setCardBackgroundColor(resources.getColor(R.color.black))
            binding.scienceTab.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.allTxt.setTextColor(resources.getColor(R.color.white))
            binding.businessTxt.setTextColor(resources.getColor(R.color.white))
            binding.entertainmentTxt.setTextColor(resources.getColor(R.color.white))
            binding.generalTxt.setTextColor(resources.getColor(R.color.white))
            binding.healthTxt.setTextColor(resources.getColor(R.color.white))
            binding.scienceTxt.setTextColor(resources.getColor(R.color.black))
            binding.fragmentContainer.visibility  = View.GONE
           viewModel.getCategoryNewsList("science")

       }


    }



    private fun onClickTab(category : String){

    }

    private fun openFrag() {
        val fm: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =
            fm.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, Home.newInstance(list))
        fragmentTransaction.commit()
    }


    fun internetIsConnected(): Boolean {

            val cm =  getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()!!.isConnected();


    }



    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        GlobalBus.sbus?.unregister(this)
    }





}