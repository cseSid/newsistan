package com.sid.newsistan.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.sid.newsistan.R


class viewPagerAdapter(private val context: Context) : PagerAdapter() {



    val image  = arrayListOf<Int>(R.drawable.news_reporter,R.drawable.signal_satellite,R.drawable.van)



    override fun getCount(): Int {
        return  image.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object` as ConstraintLayout
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflate.inflate(R.layout.view_pagger_view,container,false)
        val slideImage = view.findViewById<ImageView>(R.id.icon_image_toggle)
        slideImage.setImageResource(image[position])
        container.addView(view)
        return view

    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)

    }


}