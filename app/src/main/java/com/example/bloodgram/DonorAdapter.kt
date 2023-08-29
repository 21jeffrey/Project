package com.example.bloodgram

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.bloodgram.models.Donors

class DonorAdapter(var mctx:Context, var resources: Int, var items:List<Donors>):ArrayAdapter<Donors>(mctx,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater=LayoutInflater.from(mctx)
        val view:View=layoutInflater.inflate(resources,null)
        val imageview:ImageView=view.findViewById(R.id.profilepic)
        val title:TextView=view.findViewById(R.id.personname)
        val descript:TextView=view.findViewById(R.id.bloodtype)



        var mitems:Donors=items[position]
        imageview.setImageDrawable(mctx.resources.getDrawable(mitems.Imageid))
        title.text=mitems.username
        descript.text=mitems.bloodgroup


        return view
    }
}
