package com.example.final_project.activites

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.final_project.Layouts.MainLayout
import com.example.final_project.R
import com.example.final_project.databinding.ActivityEditProdileBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.accountModel
import java.io.ByteArrayOutputStream

class EditProdileActivity : AppCompatActivity() {

    lateinit var account :accountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     var binding = ActivityEditProdileBinding.inflate(layoutInflater)
        setContentView(binding.root)




        var db = SqlDataBase(this)


        var shard = getSharedPreferences("loginpref", AppCompatActivity.MODE_PRIVATE)

        var idd = shard.getInt("accountid",0)
        var accounts = db.getAllaccounts()
        for (acc : accountModel in accounts){
            if (acc.id==idd)
                account=acc

        }

        binding.fname.setText(account.name)
        binding.edtEmail.setText(account.email)
        binding.profileImage.setImageBitmap(
            BitmapFactory.decodeByteArray(
                account.imageUrl,
                0,
                account.imageUrl.size
            )
        )
        binding.edtPerthDAy.setText(account.perthDay)
        binding.edtPassword.setText(account.password)



       binding.btnRigester.setOnClickListener {

           var name = binding.fname.text
           var email = binding.edtEmail.text
           var pas = binding.edtPassword.text
           var opf = binding.edtPerthDAy.text

          if (name?.isNotEmpty()!!&&email?.isNotEmpty()!!&&pas?.isNotEmpty()!!&&opf?.isNotEmpty()!!){

              var bitmap = (binding.profileImage.drawable as BitmapDrawable). bitmap
              val outbutStre= ByteArrayOutputStream()
              bitmap.compress(Bitmap.CompressFormat.JPEG,20,outbutStre)
              var byteArray = outbutStre.toByteArray()

              if (  db.ubdateaccount(idd,name.toString(),email.toString(),pas.toString(),byteArray,opf.toString())){
                  Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                  startActivity(Intent(this,MainLayout::class.java))
                  finish()
              }else{

                  Toast.makeText(this, "Updated fail", Toast.LENGTH_SHORT).show()

              }

          } else{
              Toast.makeText(this, "can't be null", Toast.LENGTH_SHORT).show()

          }
       }
        binding.delete.setOnClickListener {

     var alertDialog = AlertDialog.Builder(this)
 alertDialog.setTitle("Delete Account")
            alertDialog.setMessage("are You sure to delete your Account ?")
            alertDialog.setIcon(R.drawable.ic_baseline_delete_24)

            alertDialog.setPositiveButton("ok", DialogInterface.OnClickListener { dd, _ ->

                  if (db.deleteAccount(idd)){
                      Toast.makeText(this, "Account was deleted", Toast.LENGTH_SHORT).show()
                      startActivity(Intent(this,LoginActivity::class.java))
                      finish()

                  }
             dd.dismiss()

            })

            alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dd, _ ->
                dd.dismiss()
            })
            alertDialog.show()
        }
    }

}