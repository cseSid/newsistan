package com.sid.newsistan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.sid.newsistan.MainActivity

import com.sid.newsistan.R
import com.sid.newsistan.adapter.viewPagerAdapter
import com.sid.newsistan.databinding.ActivityEntryScreenBinding

class EntryScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityEntryScreenBinding
    lateinit var viewPager : viewPagerAdapter
     var dots  = arrayOfNulls<TextView>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityEntryScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dots[0] = TextView(this)
        dots[1] = TextView(this)
        dots[2] = TextView(this)


        binding.back.setOnClickListener{

            if(getItem(0) > 0){
                binding.toggelScreen.setCurrentItem(getItem(-1),true)
            }

        }

        binding.next.setOnClickListener{

            if(getItem(0) < 2){
                binding.toggelScreen.setCurrentItem(getItem(1),true)
            }else{
                val i  = Intent(this@EntryScreenActivity,MainActivity :: class.java)
                       startActivity(i)
                        finish()
            }




        }

        binding.skip.setOnClickListener{

            val i  = Intent(this@EntryScreenActivity,MainActivity :: class.java)
            startActivity(i)
            finish()



        }


        viewPager = viewPagerAdapter(this)
        binding.toggelScreen.adapter =  viewPager
          setIndicator(0)
        binding.toggelScreen.addOnPageChangeListener(viewListener)






    }


    fun setIndicator(position:Int){

        binding.mDotLayout.removeAllViews()


        for(d in dots){
            d?.text = Html.fromHtml("&#8226")
            d?.textSize = 35F
            d?.setTextColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800,application.theme))
            binding.mDotLayout.addView(d)
        }

        dots[position]?.setTextColor(resources.getColor(R.color.black,application.theme))


    }


    private val viewListener  = object : ViewPager.OnPageChangeListener{
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
           setIndicator(position)

            if (position>0){
                binding.back.visibility = View.VISIBLE
            }else{
                binding.back.visibility = View.INVISIBLE
            }

        }

        override fun onPageScrollStateChanged(state: Int) {

        }

    }



    fun getItem (i : Int) :  Int{
        return  binding.toggelScreen.currentItem + i
    }







}