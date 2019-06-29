package com.appsco.photoeditor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_saved_images.*

class SavedImages : AppCompatActivity() {

 lateinit var savedimages:ArrayList<Bitmap>

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_images)

        savedimages= ArrayList()

        supportActionBar!!.setTitle("Drawings")
        window.navigationBarColor=resources.getColor(R.color.colorPrimaryDark)

        val cursor=DatabaseHelper(this).getImages()
        while (cursor.moveToNext()){

            val image=cursor.getBlob(cursor.getColumnIndex("image_data"))
            val bitmap=BitmapFactory.decodeByteArray(image,0,image.size)
            savedimages.add(bitmap)
        }


        saveImages.layoutManager= GridLayoutManager(this,2) as RecyclerView.LayoutManager?
        saveImages.adapter=SavedAdapter(this,savedimages)

    }
}
