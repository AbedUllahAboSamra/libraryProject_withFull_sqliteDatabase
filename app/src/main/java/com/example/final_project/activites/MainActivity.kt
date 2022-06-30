package com.example.final_project.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.databinding.ActivityMainBinding
import android.view.animation.AnimationUtils
import com.example.final_project.Layouts.MainLayout
import com.example.final_project.R
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var shard = this.getSharedPreferences("loginpref", MODE_PRIVATE)


        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_lide)
            binding.sadads.startAnimation(sideAnimation)

      var a =  shard.getBoolean("saveLogin",false)
        thread {
             Thread.sleep(3000)

             if (a){
             val intent = Intent(this, MainLayout::class.java)
             startActivity(intent)
             finish()
             }else {
                 val intent = Intent(this, LoginActivity::class.java)
                 startActivity(intent)
                 finish()
             }

         }












    }
}