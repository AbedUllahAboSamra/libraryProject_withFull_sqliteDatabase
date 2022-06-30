package com.example.final_project.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.R
import com.example.final_project.databinding.ActivityToReedBooKactivityBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels

class ToReedBooKActivity : AppCompatActivity() {
   lateinit var book :BookModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityToReedBooKactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = SqlDataBase(this)
        var id = intent.getIntExtra("bookToReadas", -1)


        var data = db.getAllBooks()
        for (b : BookModels in data){
            if (b.id==id){
                book=b
            }
        }


        binding.autherbook.text = book.auther
        binding.bookName1.text = book.bookName
        binding.bookdescription.text = book.describtion
       binding.imageView.setImageResource(book.imagURL)
binding.bottom.setOnClickListener {

    var i = Intent(this,BookContentActivity::class.java)
    i.putExtra("bookToRead120",book.id)
    startActivity(i)


}

    }
}