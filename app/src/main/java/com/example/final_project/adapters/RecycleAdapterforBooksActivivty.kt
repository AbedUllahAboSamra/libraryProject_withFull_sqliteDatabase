package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.activites.BooksActivity
import com.example.final_project.activites.ToReedBooKActivity
import com.example.final_project.databinding.TasmemBooksLayoutForRecycleBinding
import com.example.final_project.databinding.TasmemHomeFragmentForRecycleBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels
import com.example.final_project.models.GroobBooks

class RecycleAdapterforBooksActivivty(var data: ArrayList<BookModels>) :
    RecyclerView.Adapter<RecycleAdapterforBooksActivivty.myViewHoldeer1>() {

    lateinit var context: Context
lateinit var db :SqlDataBase
    class myViewHoldeer1(val binding: TasmemBooksLayoutForRecycleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHoldeer1 {
        val binding = TasmemBooksLayoutForRecycleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        context = parent.context
       db = SqlDataBase(context)
        return myViewHoldeer1(binding)

    }

    override fun onBindViewHolder(holder: myViewHoldeer1, position: Int) {

        holder.binding.bookimage.setImageResource(data[position].imagURL)
        holder.binding.bookName.text = data[position].bookName
        holder.binding.bookauther.text = data[position].auther
        holder.binding.bookyear.text = data[position].year
        holder.binding.numberOfPages.text = data[position].numberOfPages.toString()

      if (data[position].isFavorite.equals("true")) {
          holder.binding.isfevaret.setImageResource(R.drawable.ic_favaret_true)
      }else  holder.binding.isfevaret.setImageResource(R.drawable.ic_favorite_false)

        holder.binding.ratingBar.rating = data[position].rating

        holder.binding.root.setOnClickListener {
            notifyDataSetChanged()
            var i = Intent(context,ToReedBooKActivity::class.java)
            i.putExtra("bookToReadas",data[position].id)

            context.startActivity(i)

        }

 holder.binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
     db.updateisRating(data[position].id,rating)

          }

        holder.binding.isfevaret.setOnClickListener {
            notifyDataSetChanged()

            if (data[position].isFavorite.equals("false")) {
                data[position].isFavorite = "true"
                holder.binding.isfevaret.setImageResource(R.drawable.ic_favaret_true)
              db.updateisFavoret(data[position].id,"true")
                notifyDataSetChanged()

            } else {
                data[position].isFavorite = "false"
                holder.binding.isfevaret.setImageResource(R.drawable.ic_favorite_false)
                db.updateisFavoret(data[position].id,"false")
                notifyDataSetChanged()
            }
        }}

    override fun getItemCount(): Int {
        return data.size
    }
    }

