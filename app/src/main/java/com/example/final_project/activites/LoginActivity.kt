package com.example.final_project.activites

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.final_project.Layouts.MainLayout
import com.example.final_project.R
import com.example.final_project.databinding.ActivityLoginBinding
import com.example.final_project.databinding.ActivityMainBinding
import com.example.final_project.dp.SqlDataBase
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var shard = getSharedPreferences("loginpref", MODE_PRIVATE)
        var edit = shard.edit()

        val db = SqlDataBase(this)



        binding.btnLogin.setOnClickListener{

    var emeil = binding.edtEmail.text.toString()
    var pass = binding.edtPassword.text.toString()
    if (emeil.isEmpty() ||pass.isEmpty()){
        Toast.makeText(this, "Fail Login ", Toast.LENGTH_LONG).show()
    }else{


     if (db.loginaccount(emeil, pass)){
         edit.putInt("accountid",db.accountid(emeil, pass))
         edit.apply()
         var bild = AlertDialog.Builder(this)
         bild.setTitle("Save Login")
         bild.setCancelable(true)
         bild.setIcon(R.drawable.ic_round_save_24)
         bild.setMessage("Click Ok to Save Login ")
         bild.setPositiveButton("ok", DialogInterface.OnClickListener { dd, _ ->
             startActivity(Intent(this,MainLayout::class.java))
             edit.putBoolean("saveLogin",true)
             edit.apply()


             dd.dismiss()
 })

         bild.setNegativeButton("Cancel", DialogInterface.OnClickListener { dd, _ ->

             edit.putBoolean("saveLogin",false)
             edit.apply()
             dd.dismiss()
             startActivity(Intent(this,MainLayout::class.java))

         })
         bild.show()




     }else{ Toast.makeText(this, "Email or Password false", Toast.LENGTH_LONG).show()




     }


    }




}
        binding.btnRigester.setOnClickListener {
            startActivity(Intent(this,RigisterActivity::class.java))

        }




    }




}