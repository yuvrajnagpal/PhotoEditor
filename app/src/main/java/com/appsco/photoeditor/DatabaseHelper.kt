package com.appsco.photoeditor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException



class DatabaseHelper(context: Context):SQLiteOpenHelper(context,"Images",null,1){


    private val DB_TABLE = "table_image"

    // column names
    private val KEY_NAME = "image_name"
    private val KEY_IMAGE = "image_data"

    override fun onCreate(db: SQLiteDatabase?) {

        val query="CREATE TABLE " + DB_TABLE + "("+
                KEY_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_IMAGE + " BLOB);"

        db!!.execSQL(query)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
        onCreate(db)
    }



    fun addImage( image: ByteArray) {
        val database = this.writableDatabase
        val cv = ContentValues()
        cv.put(KEY_IMAGE, image)
        database.insert(DB_TABLE, null, cv)
    }


    fun getImages():Cursor{

        val db=readableDatabase
        return db.rawQuery("SELECT * FROM $DB_TABLE", null)

    }

}