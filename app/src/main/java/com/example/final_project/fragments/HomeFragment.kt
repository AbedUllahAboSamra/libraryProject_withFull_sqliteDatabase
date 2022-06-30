package com.example.final_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.RecycleAdapterforHomeFragment
import com.example.final_project.databinding.FragmentHomeBinding
import com.example.final_project.models.GroobBooks

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var root =  FragmentHomeBinding.inflate(layoutInflater)

         root.homeRecycleViewId.layoutManager=GridLayoutManager(activity,2)

        var data = ArrayList<GroobBooks>()
        data.add(GroobBooks(R.drawable.cooking,"Cook"))
        data.add(GroobBooks(R.drawable.wordwar,"World War"))
        data.add(GroobBooks(R.drawable.khial,"Fiction"))
        data.add(GroobBooks(R.drawable.wars,"Wars"))
        data.add(GroobBooks(R.drawable.sare,"Secrets"))
        data.add(GroobBooks(R.drawable.romansy,"Romantic"))
        data.add(GroobBooks(R.drawable.mybook,"My book"))
        data.add(GroobBooks(R.drawable.qissas_karton,"Cartoon stories"))
        data.add(GroobBooks(R.drawable.ediocation,"Education"))
        data.add(GroobBooks(R.drawable.cyasa,"Policy"))
        data.add(GroobBooks(R.drawable.other,"Other"))

        var adapter = RecycleAdapterforHomeFragment(data)

        root.homeRecycleViewId.adapter=adapter
        return root.root
    }
}
