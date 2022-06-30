package com.example.final_project.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.R
import com.example.final_project.databinding.ActivityMainLayoutBinding
import com.example.final_project.databinding.ActivityRigisterBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.fragments.AddBookFragment
import com.example.final_project.fragments.FavoriteFragment
import com.example.final_project.fragments.HomeFragment
import com.example.final_project.fragments.profileFragment
import com.example.final_project.models.BookModels

class MainLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.menuBottom.setItemSelected(R.id.ahome)
        binding.menuBottom.setOnItemSelectedListener {
            when (it) {
                R.id.ahome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, HomeFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.afavorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FavoriteFragment())
                        .addToBackStack(null).commit()

                }
                R.id.aperson -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, profileFragment())
                        .addToBackStack(null).commit()

                }
                R.id.aadd -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, AddBookFragment())
                        .addToBackStack(null).commit()


                }
            }
        }
    }

}


