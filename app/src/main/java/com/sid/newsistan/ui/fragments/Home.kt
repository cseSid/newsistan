package com.sid.newsistan.ui.fragments


import android.annotation.SuppressLint

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sid.newsistan.R
import com.sid.newsistan.adapter.CustomAdapter
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.viewmodel.DashBoardViewModel


class Home : Fragment() {

    val NEWS_LIST_VALUE = "newsListValue"
    lateinit var list: ArrayList<Articles>
    private lateinit var recyclerview : RecyclerView
    private lateinit var progressBar : ProgressBar
    lateinit var viewModel : DashBoardViewModel
    lateinit var  adapter : CustomAdapter



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_home, container, false)

        progressBar = v.findViewById(R.id.progress_bar)

        progressBar.visibility = View.GONE

        viewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]
        recyclerview = v.findViewById(R.id.rv_news_list)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        // This will pass the ArrayList to our Adapter
         adapter = CustomAdapter(list,requireContext(),this)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        viewModel.seccessfullInsert.observe(viewLifecycleOwner, Observer {
            if(it == -1L){
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),"Saved Failed",Toast.LENGTH_SHORT).show()
            }else{
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),"News Saved",Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.seccessfullDelete.observe(viewLifecycleOwner, Observer {
            if(it == 0){
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),"DeleteFailed",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"News Deleted",Toast.LENGTH_SHORT).show()
                viewModel.getNewsDataFromDb()
            }
        })
        return  v
    }

    companion object {
        fun newInstance(item : ArrayList<Articles>) =
            Home().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(NEWS_LIST_VALUE, item)
                    putParcelableArrayList(NEWS_LIST_VALUE, item)
                }
            }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = -1
        position = try {
            ( adapter as CustomAdapter).getPosition()
        } catch (e: Exception) {
            Log.d("TAG", e.localizedMessage, e)
            return super.onContextItemSelected(item)
        }
        when (item.title) {
           "Save" -> {
               progressBar.visibility = View.VISIBLE
               viewModel.saveDataLocally(list[position])
           }
            "Delete" ->{
                progressBar.visibility = View.VISIBLE
                viewModel.deleteDataLocally(list[position])
            }

        }
        return super.onContextItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(recyclerview);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments?.containsKey(NEWS_LIST_VALUE)!!){
            list = arguments?.getParcelableArrayList(NEWS_LIST_VALUE)!!
        }
    }







}