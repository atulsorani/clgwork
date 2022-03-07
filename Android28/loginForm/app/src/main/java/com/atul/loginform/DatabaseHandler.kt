package com.atul.loginform

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MYDB"
val TABLE_NAME = "MYUSER"
val COL_ID = "id"
val COL_MAIL = "mail"
val COL_USERNM = "usernm"
val COL_PAS = "pas"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(dp: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MAIL+" VARCHAR(50)," +
                COL_USERNM+" VARCHAR(50)," +
                COL_PAS+" VARCHAR(50))";

        dp?.execSQL(createTable)
    }

    override fun onUpgrade(dp: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun inserData(user : Userr){
        val dp = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_MAIL,user.mail)
        cv.put(COL_USERNM,user.usernm)
        cv.put(COL_PAS,user.pas)
        var result = dp.insert(TABLE_NAME,null,cv)

        if (result == -1.toLong())
            Toast.makeText(context,"Faild",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
    }

}