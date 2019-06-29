package com.appsco.photoeditor

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        supportActionBar!!.title = "Gallery"
        window.navigationBarColor=resources.getColor(R.color.colorPrimaryDark)

        val sketchList=ArrayList<Int>()
        sketchList.add(R.drawable.butterfly)
        sketchList.add(R.drawable.earth)
        sketchList.add(R.drawable.cup)
        sketchList.add(R.drawable.mirrorandbrush)
        sketchList.add(R.drawable.flower)
        sketchList.add(R.drawable.rabbit)


        sketches.layoutManager=GridLayoutManager(this,2)
        sketches.adapter=Adapter(this,sketchList)
    }
}
