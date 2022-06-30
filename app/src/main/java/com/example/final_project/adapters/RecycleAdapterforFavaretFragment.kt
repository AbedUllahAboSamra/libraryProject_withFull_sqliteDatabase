package com.example.final_project.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.activites.ToReedBooKActivity
import com.example.final_project.databinding.TasmemBooksLayoutForRecycleBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels

class RecycleAdapterforFavaretFragment(var data: ArrayList<BookModels>) :
    RecyclerView.Adapter<RecycleAdapterforFavaretFragment.myViewHoldeer12>() {

    lateinit var context: Context
    lateinit var db : SqlDataBase
    class myViewHoldeer12(val binding: TasmemBooksLayoutForRecycleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHoldeer12 {
        val binding = TasmemBooksLayoutForRecycleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        context = parent.context
        db = SqlDataBase(context)
        return myViewHoldeer12(binding)

    }

    override fun onBindViewHolder(holder: myViewHoldeer12, position: Int) {
        holder.binding.bookimage.setImageResource(data[position].imagURL)
        holder.binding.bookName.text = data[position].bookName
        holder.binding.bookauther.text = data[position].auther
        holder.binding.bookyear.text = data[position].year
        holder.binding.numberOfPages.text = data[position].numberOfPages.toString()

        if (data[position].isFavorite.equals("true"))
            holder.binding.isfevaret.setImageResource(R.drawable.ic_favaret_true)

        holder.binding.ratingBar.rating = data[position].rating

        holder.binding.root.setOnClickListener {
            notifyDataSetChanged()
            var i = Intent(context, ToReedBooKActivity::class.java)
            i.putExtra("bookToReadas",data[position].id)
            context.startActivity(i)

        }
        holder.binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            db.updateisRating(data[position].id,rating)

        }

        holder.binding.isfevaret.setOnClickListener {
            var bild = AlertDialog.Builder(context)
            bild.setTitle("UnLike")
            bild.setCancelable(true)
            bild.setIcon(R.drawable.ic_baseline_delete_24)
            bild.setMessage("When you unLike you are remove item from your faverit book...")
            bild.setPositiveButton("ok",DialogInterface.OnClickListener { dd, _ ->

                holder.binding.isfevaret.setImageResource(R.drawable.ic_favorite_false)
                db.updateisFavoret(data[position].id,"false")
                data.removeAt(position)
                dd.dismiss()
                notifyDataSetChanged()

            })

      bild.setNegativeButton("Cancel", DialogInterface.OnClickListener { dd, _ ->
          dd.dismiss()

      })
            bild.show()



            }
 }

    override fun getItemCount(): Int {
        return data.size
    }
}

