package com.example.final_project.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.final_project.R
import com.example.final_project.databinding.FragmentAddBookBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.BookModels
import java.util.*

class AddBookFragment : Fragment() {


    var galaryRequist = 1000
    private lateinit var binding: FragmentAddBookBinding
    var d: String = ""

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var db = SqlDataBase(context!!)
        binding = FragmentAddBookBinding.inflate(layoutInflater)


        binding.bookimage.setOnClickListener {

            val galary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activity!!.startActivityForResult(galary, galaryRequist)
        }


        binding.addBookYear.setOnClickListener {
            val curruntDate = Calendar.getInstance()
            var day = curruntDate.get(Calendar.DAY_OF_MONTH)
            var year = curruntDate.get(Calendar.YEAR)
            var month = curruntDate.get(Calendar.MONTH)


            var picker = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.addBookYear.setText("$year:$month:$day")
                },
                year,
                month,
                day
            )

            picker.show()

        }


        binding.floatingActionButton.setOnClickListener {
            var bookCategory = "Other"
            var type = binding.bookCategory.text.toString()
            when (type) {

                "Cook" -> {
                    bookCategory = "Cook"
                }
                "World War" -> {
                    bookCategory = "World War"
                }
                "Fiction" -> {
                    bookCategory = "Fiction"
                }
                "Secrets" -> {
                    bookCategory = "Secrets"
                }
                "Romantic" -> {
                    bookCategory = "Romantic"
                }
                "Education" -> {
                    bookCategory = "Education"
                }
                "Wars" -> {
                    bookCategory = "Wars"
                }
                "Wars" -> {
                    bookCategory = "Wars"
                }
                "Policy" -> {
                    bookCategory = "Policy"
                }
                "Cartoon stories" -> {
                    bookCategory = "Cartoon stories"
                }
                else -> {
                    bookCategory = "Other"
                }
            }

            var bookName: String = binding.addBookName.getText().toString()
            var bookYear = binding.addBookYear.text.toString()
            var bookAuthor = binding.addBookAuther.text.toString()
            var boookLanguage = binding.addBooklanguage.text.toString()
            var boookContent = binding.addBookContent.text.toString()
            var bookDiscribtion = binding.addBookDisc.text.toString()
            var boookImageUrl = d
            var numOfBages = binding.addBookContent.lineCount

            var image: Int?

            if (d.isEmpty()) {
                image = R.drawable.newbook
            } else image = null




            if (
                bookName.isEmpty() &&
                bookYear.isEmpty() &&
                bookAuthor.isEmpty() &&
                boookLanguage.isEmpty() &&
                boookContent.isEmpty() &&
                bookDiscribtion.isEmpty()
            ) {
                Toast.makeText(context, "Book data cannot be empty. ", Toast.LENGTH_SHORT).show()


            } else {


                var a = db.addBook(
                       bookName,
                       bookAuthor,
                       bookYear,
                       bookCategory,
                       bookDiscribtion,
                       boookLanguage,
                       numOfBages,
                       "false",

                       image,
                      boookContent,
                      1F,
                      boookImageUrl,
                      "t"
                )

             if (a)   Toast.makeText(context, "The Book was Added in $bookCategory Category", Toast.LENGTH_LONG).show()
            else  Toast.makeText(context, "your Book was not Added", Toast.LENGTH_SHORT).show()

            }


        }




        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == galaryRequist) {
            binding.bookimage.setImageURI(data?.data)
            d = data!!.data!!.toString()
        }
    }
}