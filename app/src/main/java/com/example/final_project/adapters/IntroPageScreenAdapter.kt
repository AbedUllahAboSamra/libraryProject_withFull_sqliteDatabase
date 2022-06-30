package com.example.final_project.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.final_project.R
import com.example.final_project.databinding.ActivityIntroBinding
import com.example.final_project.databinding.LayoutScreaanBinding
import com.example.final_project.models.ScreenIntroItem

class IntroPageScreenAdapter(var context: Context,  var data : ArrayList<ScreenIntroItem>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {


      var view = LayoutScreaanBinding.inflate(LayoutInflater.from(context),null,false)

 view.introFiscrebtion11.setText(data[position].discrbition)
 view.introImage.setImageResource(data[position].imageDrawable)

container.addView(view.root)

        return view.root

    }

    override fun getCount(): Int {
 return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return  view==`object`

      }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

     container.removeView(`object`as View)
}
}