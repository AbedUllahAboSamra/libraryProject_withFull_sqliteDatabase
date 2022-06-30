package com.example.final_project.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.databinding.ActivityBookContentBinding
import com.example.final_project.databinding.ActivityBooksBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels

class BookContentActivity : AppCompatActivity() {
    lateinit var book:BookModels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      var binding = ActivityBookContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = SqlDataBase(this)

        var id = intent.getIntExtra("bookToRead120", -1)


        var data = db.getAllBooks()
        for (b : BookModels in data){
            if (b.id==id){
                book=b
        }
        }
        binding.appBarImage.setImageResource(book.imagURL)
        binding.content.text=book.content
    }
}