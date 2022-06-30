package com.example.final_project.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.activites.EditProdileActivity
import com.example.final_project.activites.LoginActivity
import com.example.final_project.adapters.MyProfileBooksAdapter
import com.example.final_project.databinding.FragmentProfileBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.accountModel

class profileFragment : Fragment() {


    lateinit var account: accountModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentProfileBinding.inflate(layoutInflater)

        var db = SqlDataBase(context!!)

        var accounts = db.getAllaccounts()
        var shard = activity!!.getSharedPreferences("loginpref", AppCompatActivity.MODE_PRIVATE)

        var idd = shard.getInt("accountid", 0)

        for (acc: accountModel in accounts) {
            if (acc.id == idd)
                account = acc
        }

        binding.accountName.setText(account.name)
        binding.accountemail.setText(account.email)
        binding.profileImage.setImageBitmap(
            BitmapFactory.decodeByteArray(
                account.imageUrl,
                0,
                account.imageUrl.size
            )
        )
        binding.accountphone.setText(account.perthDay)

        var data = db.getMyBook()

        if (data.size > 0) {
            binding.imageView2.isVisible = false
            binding.textView6.isVisible = false
            binding.AddingBookmarks.isVisible = false
        } else {
            binding.imageView2.isVisible = true
            binding.textView6.isVisible = true
            binding.AddingBookmarks.isVisible = true
        }
        binding.recycle.layoutManager = LinearLayoutManager(context)


        var adapter = MyProfileBooksAdapter(data)

        binding.recycle.adapter = adapter

        binding.optionMenipo.setOnClickListener {

            var pop = PopupMenu(context, binding.optionMenipo)
            pop.menuInflater.inflate(R.menu.option_menu, pop.menu)
            pop.setOnMenuItemClickListener { x ->
                when (x.itemId) {
                    R.id.logout -> {
                        context!!.startActivity(
                            Intent(
                                context,
                                LoginActivity::class.java
                            )
                        )
                    }
                    R.id.setting -> {

                        context!!.startActivity(
                            Intent(
                                context,
                                EditProdileActivity::class.java
                            )
                        )

                    }
                }
                true
            }
            pop.show()


        }


        return binding.root

    }


}