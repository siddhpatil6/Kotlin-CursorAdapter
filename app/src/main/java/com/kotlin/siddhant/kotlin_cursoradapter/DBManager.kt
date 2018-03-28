package com.kotlin.siddhant.kotlin_cursoradapter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Siddhant on 26/03/18.
 */
public class DBManager(context: Context):SQLiteOpenHelper(context,"DATABASE",null,1)
{
    var contextz=context


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE DATA (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertUser(name:String):Boolean
    {
        val db=writableDatabase
        var value=ContentValues()
        value.put("NAME",name)
        var id=db.insert("DATA",null,value)
        return true
    }
}