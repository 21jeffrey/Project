package com.example.bloodgram


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.example.bloodgram.models.Donors



class HomepageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val mylistview=findViewById<ListView>(R.id.lv_users)
        var list= mutableListOf<Donors>()
        list.add(Donors("John Muchiri","A+", R.drawable.a,))
        list.add(Donors("Lucy Mutena","AB-", R.drawable.b,))
        list.add(Donors("James Kamau","O+", R.drawable.d))
        list.add(Donors("Luis Kyalo","O-", R.drawable.f))


        mylistview.adapter=DonorAdapter(this,R.layout.list_item,list)



        mylistview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val intent = Intent(this@HomepageActivity, UserActivity::class.java)
            startActivity(intent)
        }







    }

    }





