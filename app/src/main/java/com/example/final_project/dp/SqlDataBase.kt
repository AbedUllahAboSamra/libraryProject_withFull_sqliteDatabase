package com.example.final_project.dp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.final_project.models.BookModels
import com.example.final_project.models.accountModel

class SqlDataBase(var context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERISON
){

    private lateinit var db: SQLiteDatabase

    init {
        db = this.writableDatabase
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(accountModel.TABLECREATE)
        db!!.execSQL(BookModels.TABLECREATE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(accountModel.TABLEDROP)
        db!!.execSQL(BookModels.TABLEDROP)




        onCreate(db)
    }
//**************************


    fun addAccount(
        name: String,
        email: String,
        password: String,
        ImageUrl: ByteArray,
        pertday: String
    ): Boolean {
        val cValues = ContentValues()
        cValues.put(accountModel.NAME_CULOMN, name)
        cValues.put(accountModel.EMAIL_CULOMN, email)
        cValues.put(accountModel.PASSWORD_CULOMN, password)
        cValues.put(accountModel.IMAGEURL_CULOMN, ImageUrl)
        cValues.put(accountModel.perthDay_CULOMN, pertday)
        return db.insert(accountModel.TABLE_NAME, null, cValues) > 0
    }


    fun loginaccount(email: String, password: String): Boolean {
        var accounts = getAllaccounts()

        var bool = false

        for (a: accountModel in accounts) {

            if (a.email.equals(email) && a.password.equals(password)) {
                bool = true
                break
            }

        }
        return bool
    }

    fun accountid(email: String, password: String): Int {
        var accounts = getAllaccounts()

        var acId = 0
        for (a: accountModel in accounts) {

            if (a.email.equals(email) && a.password.equals(password)) {
                acId = a.id
                break
            }
        }
        return acId
    }

    fun deleteAccount(id: Int): Boolean {
        return db.delete(accountModel.TABLE_NAME, "${accountModel.ID_CULOMN}=?", arrayOf("$id")) > 0
    }

    fun getAllaccounts(): ArrayList<accountModel> {
        var accounts = ArrayList<accountModel>()
        val c = db.rawQuery(
            "select * from ${accountModel.TABLE_NAME} order by ${accountModel.ID_CULOMN}",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val a = accountModel(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getBlob(4),
                c.getString(5)
            )
            accounts.add(a)
            c.moveToNext()
        }
        c.close()
        return accounts
    }


    fun resetPassword(email: String): String {
        var accounts = getAllaccounts()
        var bas = ""
        for (a: accountModel in accounts) {
            if (a.email.equals(email)) {
                bas = a.password
                break
            }
        }
        return bas
    }
/* التعديل هنا */
    fun ubdateaccount(
        oldId: Int,
        name: String,
        email: String,
        password: String,
        ImageUrl: ByteArray,
        pertday: String,
    ): Boolean {
        val cValues = ContentValues()
        cValues.put(accountModel.NAME_CULOMN, name)
        cValues.put(accountModel.EMAIL_CULOMN, email)
        cValues.put(accountModel.PASSWORD_CULOMN, password)
        cValues.put(accountModel.IMAGEURL_CULOMN, ImageUrl)
        cValues.put(accountModel.perthDay_CULOMN, pertday)
        return db.update(
            accountModel.TABLE_NAME,
            cValues,
            "${accountModel.ID_CULOMN}=?",
            arrayOf("$oldId")
        ) > 0
    }

//**********************************************

    fun getAllBooks(): ArrayList<BookModels> {

        var books = ArrayList<BookModels>()
        val c = db.rawQuery(
            "SELECT * FROM ${BookModels.TABLE_NAME}",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val a = BookModels(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getInt(7),
                c.getString(8),
                c.getInt(9),
                c.getString(10),
                c.getFloat(11),
                c.getString(12),
                c.getString(13),
            )
            books.add(a)
            c.moveToNext()
        }
        c.close()
        return books
    }

    fun getFevaretBooks(): ArrayList<BookModels> {
        var books = ArrayList<BookModels>()
        val c = db.rawQuery(
            "SELECT * FROM ${BookModels.TABLE_NAME} WHERE ${BookModels.ISFEVARET_CULOMN}=?",
            arrayOf("true")
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val a = BookModels(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getInt(7),
                c.getString(8),
                c.getInt(9),
                c.getString(10),
                c.getFloat(11),
                c.getString(12),
                c.getString(13)
            )
            books.add(a)
            c.moveToNext()
        }
        c.close()
        return books
    }

    fun getTybebooks(type: String): ArrayList<BookModels> {
        var books = ArrayList<BookModels>()
        var dataBase = this.readableDatabase
        val c = dataBase.rawQuery(
            "SELECT * FROM ${BookModels.TABLE_NAME} WHERE ${BookModels.CATEGORY_CULOMN}= ? ",
            arrayOf(type)
        )

        c.moveToFirst()
        while (!c.isAfterLast) {
            val a = BookModels(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),

                c.getString(5),
                c.getString(6),
                c.getInt(7),
                c.getString(8),
                c.getInt(9),
                c.getString(10),
                c.getFloat(11),
                c.getString(12),
                c.getString(13)
            )
            books.add(a)
            c.moveToNext()
        }
        c.close()
        return books

    }


    fun addBook(
        bookName: String,
        auther: String,
        year: String,
        category: String,
        describtion: String,
        language: String,
        numberOfPages: Int,
        isFavorite: String,
        imagURL: Int?,
        content: String,
        rating: Float,
        imageUrlString: String,
        fromUser: String,
    ): Boolean {
        val cValues = ContentValues()
        cValues.put(BookModels.NAME_CULOMN, bookName)
        cValues.put(BookModels.AUTER_CULOMN, auther)
        cValues.put(BookModels.YEAR_CULOMN, year)
        cValues.put(BookModels.CATEGORY_CULOMN, category)
        cValues.put(BookModels.DISCRIBTION_CULOMN, describtion)
        cValues.put(BookModels.LANGUAGE_CULOMN, language)
        cValues.put(BookModels.NUMBEROFPAGES_CULOMN, numberOfPages)
        cValues.put(BookModels.ISFEVARET_CULOMN, isFavorite)
        cValues.put(BookModels.IMAGEURL_CULOMN, imagURL)
        cValues.put(BookModels.CONTENT_CULOMN, content)
        cValues.put(BookModels.RATING_CULOMN, rating)
        cValues.put(BookModels.IMAGEURLSTRING_CULOMN, imageUrlString)
        cValues.put(BookModels.FROMUSER_CULOMN, fromUser)
        return db.insert(BookModels.TABLE_NAME, null, cValues) > 0
    }


    fun getMyBook(): ArrayList<BookModels> {


        var books = ArrayList<BookModels>()
        val c = db.rawQuery(
            "SELECT * FROM ${BookModels.TABLE_NAME} WHERE ${BookModels.FROMUSER_CULOMN}=?",
            arrayOf("t")
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val a = BookModels(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getInt(7),
                c.getString(8),
                c.getInt(9),
                c.getString(10),
                c.getFloat(11),
                c.getString(12),
                c.getString(13),
            )
            books.add(a)
            c.moveToNext()
        }
        c.close()
        return books

    }


    fun removeBook(id: Int): Boolean {
        return db.delete(BookModels.TABLE_NAME, "${BookModels.ID_CULOMN}=?", arrayOf("$id")) > 0
    }


    fun updateisFavoret(id: Int, isFavorite: String): Boolean {

        var book = getAllBooks()

        var c = ContentValues()
        c.put(BookModels.ISFEVARET_CULOMN, isFavorite)

        return db.update(BookModels.TABLE_NAME, c, "${BookModels.ID_CULOMN}=?", arrayOf("$id")) > 0

    }

    fun updateisRating(id: Int, rating: Float): Boolean {

        var c = ContentValues()
        c.put(BookModels.RATING_CULOMN, rating)
        return db.update(BookModels.TABLE_NAME, c, "${BookModels.ID_CULOMN}=?", arrayOf("$id")) > 0
    }


//*********************************************

    companion object {
         const val DATABASE_NAME = "LIBRARY"
         var DATABASE_VERISON = 21
    }
}