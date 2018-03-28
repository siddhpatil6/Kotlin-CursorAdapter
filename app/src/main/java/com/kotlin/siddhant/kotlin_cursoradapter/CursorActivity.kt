package com.kotlin.siddhant.kotlin_cursoradapter

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class CursorActivity : AppCompatActivity() {

    var handler:DBManager?=null
    var lv_data:ListView?=null
    var etName:EditText?=null
    var btnSubmit:Button?=null
    var todoCursor:Cursor?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursor)
        etName=findViewById(R.id.etName)
        btnSubmit=findViewById(R.id.btnSubmit)
        etName=findViewById(R.id.etName)
        lv_data=findViewById(R.id.lv_data)


        handler = DBManager(this);
        var db :SQLiteDatabase= handler!!.getWritableDatabase();
        todoCursor = db.rawQuery("SELECT * FROM DATA", null)
       // cursorLoadr.apply { todoCursor }
        var adapter=DemoAdapter(this,cursorz = todoCursor, flag = 0)
        lv_data?.adapter=adapter

        btnSubmit?.setOnClickListener {

            handler!!.insertUser(etName?.text.toString())
            todoCursor = db.rawQuery( "SELECT * FROM DATA", null);

            if (todoCursor!=null)
            {
                if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.GINGERBREAD){
                    adapter.swapCursor(todoCursor);
                } else {
                    adapter.changeCursor(todoCursor);
                }
            }

            //adapter.notifyDataSetChanged()
        }




// Get access to the underlying writeable database

// Query for items from the database and get a cursor back

    }
}
