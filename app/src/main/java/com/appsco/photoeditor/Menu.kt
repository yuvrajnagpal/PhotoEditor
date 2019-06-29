package com.appsco.photoeditor

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.alert.view.*
import top.defaults.colorpicker.ColorPickerPopup

class Menu : AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()



        supportActionBar!!.hide()
        window.navigationBarColor=resources.getColor(R.color.colorPrimaryDark)

        addnew.setOnClickListener {


            startActivity(Intent(this,GalleryActivity::class.java))
        }

        drawings.setOnClickListener {


            startActivity(Intent(this,SavedImages::class.java))
        }

        gallery.setOnClickListener {

            val intent=Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent,1)
        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==1 && data!=null){
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("image",data.data)
            startActivity(intent)
        }
    }




}
