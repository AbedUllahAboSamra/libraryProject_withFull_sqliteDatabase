package com.example.final_project.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

data class BookModels (
    var id: Int,
    var bookName: String?,
    var auther: String?,
    var year: String?,
    var category: String?,
    var describtion: String?,
    var language: String?,
    var numberOfPages: Int,
    var isFavorite: String? ="false",
    var imagURL: Int,
    var content:String?,
    var rating:Float,
    var ImageUrl:String?,
    var fromUser:String

    ) :Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeInt(id)
        dest.writeString(bookName)
        dest.writeInt(imagURL)
        dest.writeString(auther)
        dest.writeInt(numberOfPages)
        dest.writeString(describtion)
        dest.writeString(category)
        dest.writeString(isFavorite)
        dest.writeString(content)
        dest.writeString(language)
        dest.writeString(year)
        dest.writeFloat(rating)
        dest.writeString(ImageUrl)
        dest.writeString(fromUser)







    }

    companion object CREATOR : Parcelable.Creator<BookModels> {
        const val TABLE_NAME = "Book"
        const val ID_CULOMN = "id"
        const val NAME_CULOMN = "name"

        const val AUTER_CULOMN = "auther"

        const val YEAR_CULOMN = "year"
        const val CATEGORY_CULOMN = "category"
        const val DISCRIBTION_CULOMN = "describtion"
        const val LANGUAGE_CULOMN = "language"
        const val NUMBEROFPAGES_CULOMN = "numberOfPages"
        const val ISFEVARET_CULOMN = "isFavorite"
        const val IMAGEURL_CULOMN = "imageUrl"
        const val CONTENT_CULOMN = "content"
        const val RATING_CULOMN = "rating"
        const val IMAGEURLSTRING_CULOMN = "ImageUrlString"
        const val FROMUSER_CULOMN = "fromUser"



        const val TABLECREATE =
            "CREATE TABLE $TABLE_NAME ($ID_CULOMN INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$NAME_CULOMN TEXT," +
                    "$AUTER_CULOMN TEXT," +
                    "$YEAR_CULOMN TEXT," +
                    "$CATEGORY_CULOMN TEXT," +
                    "$DISCRIBTION_CULOMN TEXT," +
                    "$LANGUAGE_CULOMN TEXT," +
                    "$NUMBEROFPAGES_CULOMN INTEGER," +
                    "$ISFEVARET_CULOMN TEXT," +
                    "$IMAGEURL_CULOMN INTEGER," +
                    "$CONTENT_CULOMN TEXT," +
                    "$RATING_CULOMN FLOAT," +
                    "$IMAGEURLSTRING_CULOMN TEXT," +
                    "$FROMUSER_CULOMN TEXT)"
        const val TABLEDROP = "DROP TABLE IF EXISTS $TABLE_NAME"


        override fun createFromParcel(parcel: Parcel): BookModels {
            return BookModels(parcel)
        }

        override fun newArray(size: Int): Array<BookModels?> {
            return arrayOfNulls(size)
        }
    }


}
