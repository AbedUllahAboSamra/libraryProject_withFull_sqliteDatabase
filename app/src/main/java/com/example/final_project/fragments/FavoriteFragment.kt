package com.example.final_project.fragments

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.RecycleAdapterforBooksActivivty
import com.example.final_project.adapters.RecycleAdapterforFavaretFragment
import com.example.final_project.databinding.FragmentFavoriteBinding
import com.example.final_project.dp.SqlDataBase

class FavoriteFragment : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentFavoriteBinding.inflate(layoutInflater)
        var db = SqlDataBase(context!!)
        var date = db.getFevaretBooks()


        if (date.size > 0) {
            binding.imageView2.isVisible = false
            binding.textView6.isVisible = false
            binding.AddingBookmarks.isVisible = false
        } else {
            binding.imageView2.isVisible = true
            binding.textView6.isVisible = true
            binding.AddingBookmarks.isVisible = true
        }
        var adapter = RecycleAdapterforFavaretFragment(date)
        binding.recycleView.layoutManager = LinearLayoutManager(activity)
        binding.recycleView.adapter = adapter
        return binding.root
    }


}