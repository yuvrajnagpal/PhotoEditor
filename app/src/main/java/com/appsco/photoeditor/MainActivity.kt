package com.appsco.photoeditor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener
import ja.burhanrashid52.photoeditor.OnSaveBitmap
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.ViewType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_text.*
import kotlinx.android.synthetic.main.edit_text.view.*
import top.defaults.colorpicker.ColorPickerPopup
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var photoEditor:PhotoEditor
    lateinit var colorPickerPopup:ColorPickerPopup
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingPermission")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle=intent.extras
        val imageres=bundle.get("image")

        supportActionBar!!.hide()
        window.navigationBarColor=resources.getColor(R.color.colorPrimaryDark)

         colorPickerPopup=ColorPickerPopup.Builder(this)
            .initialColor(Color.RED) // Set initial color
            .enableBrightness(true) // Enable brightness slider or not
            .enableAlpha(true) // Enable alpha slider or not
            .okTitle("Choose")
            .cancelTitle("Cancel")
            .showIndicator(true)
            .showValue(true)
            .build()

        when (imageres) {
            is Int -> photoEditorView.source.setImageResource(imageres)
            is Bitmap -> photoEditorView.source.setImageBitmap(imageres)
            is Uri -> photoEditorView.source.setImageURI(imageres)
        }





        photoEditor= PhotoEditor.Builder(this, photoEditorView)
            .build()



        paintBrush.setOnClickListener {

            colorPickerBrush()
        }

        erasor.setOnClickListener {


            photoEditor.brushEraser()
        }



        undo.setOnClickListener {

            photoEditor.undo()
        }

        redo.setOnClickListener {

            photoEditor.redo()
        }

        text.setOnClickListener {


                colorPickerEdittext()

        }


        save.setOnClickListener {


            photoEditor.saveAsBitmap(object:OnSaveBitmap{
                override fun onFailure(e: Exception?) {

                }

                override fun onBitmapReady(saveBitmap: Bitmap?) {

                    val stream =ByteArrayOutputStream()
                    saveBitmap!!.compress(Bitmap.CompressFormat.PNG, 0, stream)
                    val bytes=stream.toByteArray()

                    try {

                        DatabaseHelper(this@MainActivity).addImage(bytes)
                        Toast.makeText(this@MainActivity,"added to drawings",Toast.LENGTH_SHORT).show()

                    }
                    catch (e:Exception){

                    }



                }

            })
        }


    }

            fun colorPickerBrush(){


            colorPickerPopup.show(photoEditorView, object : ColorPickerPopup.ColorPickerObserver() {
                override fun onColorPicked(color: Int) {
                    photoEditor.setBrushDrawingMode(true)
                    photoEditor.brushColor=color

                }


            })



    }



    fun colorPickerEdittext(){


        colorPickerPopup.show(photoEditorView, object : ColorPickerPopup.ColorPickerObserver() {
                override fun onColorPicked(color: Int) {

                    val alert=AlertDialog.Builder(this@MainActivity)
                    val view=LayoutInflater.from(this@MainActivity).
                        inflate(R.layout.edit_text,null,false)
                    alert.setView(view)
                    alert.setPositiveButton("ok",object: DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            photoEditor.addText(view.input.text.toString(),color)
                        }

                    })
                    alert.show()

                    view.input.setTextColor(color)


                }

            })



    }



}
