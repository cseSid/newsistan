package com.sid.newsistan.adapter

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.*
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.sid.newsistan.R
import com.sid.newsistan.dataClass.Articles
import com.sid.newsistan.viewmodel.DashBoardViewModel
import com.sid.newsistan.webView.WebViewActivity


class CustomAdapter(private val mList: List<Articles>,private val context : Context, private val viewModelStoreOwner: ViewModelStoreOwner) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var mWebview: WebView? = null
    lateinit var viewModel : DashBoardViewModel
    val saveClick = MutableLiveData<Boolean>()
    private var position = 0


    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_news_item, parent, false)

        viewModel = ViewModelProvider(viewModelStoreOwner)[DashBoardViewModel::class.java]

        mWebview  =  WebView(context);

        return ViewHolder(view, context)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Glide.with(context)
            .load(ItemsViewModel.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.error)
            .error(R.drawable.error)
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.title.text = ItemsViewModel.title
//        holder.bodyMsg.text = ItemsViewModel.content
        holder.time.text = ItemsViewModel.publishedAt
        holder.source.text = ItemsViewModel.author


//        holder.saveBtn.setOnClickListener {
//            saveClick.postValue(true)
//            viewModel.saveDataLocally(mList[position])
//        }


        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            setPosition(holder.position)
            false
        })


        holder.itemView.setOnClickListener(View.OnClickListener() {
                val url = mList[position].url
                val intent = Intent(context.applicationContext, WebViewActivity :: class.java)
                intent.putExtra("URL", url);
                context.startActivity(intent)
            })




    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder)
    }




    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private var ItemView: View,private val context: Context) : RecyclerView.ViewHolder(ItemView) ,View.OnCreateContextMenuListener {
        val imageView: ImageView = itemView.findViewById(R.id.newsImage)
        val title: TextView = itemView.findViewById(R.id.heading_value)

        val time : TextView = itemView.findViewById(R.id.time_value)
//        val saveBtn : MaterialCardView = itemView.findViewById(R.id.saved)
//        val bodyMsg : TextView = itemView.findViewById(R.id.body_value)
        val source : TextView = itemView.findViewById(R.id.source_value)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }



        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            if (internetIsConnected(context = context)){
                menu?.add("Save")
            }else{
                menu?.add("Delete")
            }

        }

        fun internetIsConnected(context: Context): Boolean {

            val cm =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()!!.isConnected();
        }





    }


}


 interface onClick{

     fun onItemClick(m : Boolean)

 }
