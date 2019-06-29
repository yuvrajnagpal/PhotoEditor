package com.appsco.photoeditor

import android.content.Context
import android.content.Intent
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.image_item.view.*

class Adapter( val context: Context, val sketchList:ArrayList<Int>):RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view=LayoutInflater.from(p0.context).inflate(R.layout.image_item,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sketchList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.bindView(sketchList[p1])
        p0.itemView.setOnClickListener {

            val intent=Intent(context,MainActivity::class.java)
            intent.putExtra("image",sketchList[p1])
            context.startActivity(intent)
        }
    }


    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

        fun bindView(image:Int){

            itemView.sketch.setImageResource(image)
        }
    }
}