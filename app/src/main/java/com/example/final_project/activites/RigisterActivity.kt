package com.example.final_project.activites

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.DatePicker
import android.widget.Toast
import com.example.final_project.Layouts.MainLayout
import com.example.final_project.databinding.ActivityRigisterBinding
import com.example.final_project.databinding.FragmentAddBookBinding
import com.example.final_project.dp.SqlDataBase
import java.io.ByteArrayOutputStream
import java.util.*

class RigisterActivity : AppCompatActivity() {

    var galaryRequist = 1000
    private lateinit var binding: ActivityRigisterBinding
    var d: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRigisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SqlDataBase(this)



        binding.profileImage.setOnClickListener {

            val galary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galary, galaryRequist)

        }





        binding.edtPerthDAy.setOnClickListener {
            val curruntDate = Calendar.getInstance()
            var day = curruntDate.get(Calendar.DAY_OF_MONTH)
            var year = curruntDate.get(Calendar.YEAR)
            var month = curruntDate.get(Calendar.MONTH)
            var picker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.edtPerthDAy.setText("$year:$month:$day")
                },
                year,
                month,
                day
            )

            picker.show()

        }



        binding.btnRigester.setOnClickListener {
            var fullName: String =
                binding.fname.text.toString() + " " + binding.lname.text.toString()
            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()
            var perthDay: String = binding.edtPerthDAy.text.toString()
            if (fullName.isEmpty() || perthDay.isEmpty() || email.isEmpty() || password.isEmpty() || d == null) {
                Toast.makeText(this, "fail operation", Toast.LENGTH_LONG).show()
            } else {
                // تحويل صورة من  يو ار ال بت ماب اللى بايت اري

                var bitmap = (binding.profileImage.drawable as BitmapDrawable). bitmap
                val outbutStre= ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG,20,outbutStre)
                var byteArray = outbutStre.toByteArray()
                db.addAccount(fullName,email,password,byteArray,perthDay)

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == galaryRequist) {
            binding.profileImage.setImageURI(data?.data)
            d = data!!.data

        }
    }


}