package com.example.final_project.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.RecycleAdapterforBooksActivivty
import com.example.final_project.databinding.ActivityBooksBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels

class BooksActivity : AppCompatActivity() {
    lateinit var date : ArrayList<BookModels>
    var text :String=""
    lateinit var db:SqlDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

         text = intent.getStringExtra("categoryType").toString()

          binding.bookType.text=text.toString()

         db = SqlDataBase(this)
         date = db.getTybebooks(text.toString())


        binding.recycleView.layoutManager = LinearLayoutManager(this)


        var adapter = RecycleAdapterforBooksActivivty(date)

        binding.recycleView.adapter = adapter


    }

    override fun onResume() {

        date = db.getTybebooks(text.toString())
        super.onResume()

    }
}