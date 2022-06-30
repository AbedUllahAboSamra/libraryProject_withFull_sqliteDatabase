package com.example.final_project.models

import java.sql.Blob

data class accountModel(
    var id: Int,
    var password: String,
    var name: String,
    var email: String,
    var imageUrl: ByteArray,
    var perthDay: String,
    ){

    companion object {
        const val TABLE_NAME = "Account"
        const val ID_CULOMN = "id"
        const val PASSWORD_CULOMN = "password"
        const val NAME_CULOMN = "name"
        const val EMAIL_CULOMN = "email"
        const val IMAGEURL_CULOMN = "imageUrl"
        const val perthDay_CULOMN = "perthDay"

        const val TABLECREATE =
            "create table $TABLE_NAME ($ID_CULOMN INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$PASSWORD_CULOMN TEXT NOT NULL," +
                    "$NAME_CULOMN TEXT," +
                    "$EMAIL_CULOMN TEXT," +
                    "$IMAGEURL_CULOMN BLOB," +
                    "$perthDay_CULOMN TEXT)"
        const val TABLEDROP = "DROP TABLE IF EXISTS $TABLE_NAME"
    }


}
