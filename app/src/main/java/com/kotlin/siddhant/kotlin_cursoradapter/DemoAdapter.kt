package com.kotlin.siddhant.kotlin_cursoradapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

/**
 * Created by Siddhant on 26/03/18.
 */
public class DemoAdapter(context: Context?,cursorz:Cursor?,flag:Int): CursorAdapter(context,cursorz,0)
{
    var contextz:Context?=context
    var cursorz:Cursor?=cursorz


    override fun newView(p0: Context?, p1: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(contextz).inflate(R.layout.cursor_row, parent, false);
    }

    override fun bindView(view: View?, p1: Context?, cursor: Cursor?) {
        var tvName=view?.findViewById<TextView>(R.id.tvName)
        var text=cursorz?.getColumnIndex(cursor?.getColumnName(1)!!)
        Log.d("kk-->>",text.toString())
        tvName?.text=cursorz?.getString(cursor?.getColumnIndexOrThrow("NAME")!!)
    }


}