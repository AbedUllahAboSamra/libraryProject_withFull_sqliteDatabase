package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.activites.BooksActivity
import com.example.final_project.databinding.TasmemHomeFragmentForRecycleBinding
import com.example.final_project.models.GroobBooks

class RecycleAdapterforHomeFragment(var data:ArrayList<GroobBooks>):RecyclerView.Adapter<RecycleAdapterforHomeFragment.myViewHoldeer>() {

    lateinit var  context :Context

    class myViewHoldeer(val binding: TasmemHomeFragmentForRecycleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHoldeer {
        val binding = TasmemHomeFragmentForRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      context=parent.context
        return myViewHoldeer(binding)

    }

    override fun onBindViewHolder(holder: myViewHoldeer, position: Int) {
           holder.binding.groopbookimage.setImageResource(data[position].image)
           holder.binding.groopbookname.text=data[position].groopName
           holder.binding.cardView.setOnClickListener {

           var  i = Intent(context,BooksActivity::class.java)
           i.putExtra("categoryType","${data[position].groopName}")
           context.startActivity(i)

        }

    }

    override fun getItemCount(): Int {
 return data.size
    }

}