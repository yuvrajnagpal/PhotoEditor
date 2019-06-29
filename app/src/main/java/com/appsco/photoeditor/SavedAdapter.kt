package com.appsco.photoeditor

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.saveimage_item.view.*


import java.io.*


import android.content.Intent
import android.widget.Toast

import java.util.*





class SavedAdapter(val context: Context, val sketchList:ArrayList<Bitmap>): RecyclerView.Adapter<SavedAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view= LayoutInflater.from(p0.context).inflate(R.layout.saveimage_item,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sketchList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.bindView(sketchList[p1])
        p0.itemView.setOnClickListener {


        }
        p0.itemView.share.setOnClickListener {

            val cache = context.externalCacheDir
            val sharefile = File(cache, "toshare.jpeg")
            try {
                val out = FileOutputStream(sharefile)
                sketchList[p1].compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
            } catch (e: IOException) {
                  Toast.makeText(context,"${e.toString()}",Toast.LENGTH_LONG).show()
            }

            val share = Intent(android.content.Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM,Uri.parse(sharefile.toString()))
            try {
                context.startActivity(Intent.createChooser(share, "Share photo"))
            } catch (e: Exception) {
                Toast.makeText(context,"${e.toString()}",Toast.LENGTH_LONG).show()
            }


        }
    }


    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        fun bindView(image:Bitmap){

            itemView.savesketch.setImageBitmap(image)

        }



    }
}