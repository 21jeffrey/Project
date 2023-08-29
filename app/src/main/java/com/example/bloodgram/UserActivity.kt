package com.example.bloodgram

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class UserActivity : AppCompatActivity() {
    lateinit var Btncall: Button
    lateinit var Btnemail: Button
    lateinit var Imghome:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        Btncall=findViewById(R.id.btncall)
        Btnemail=findViewById(R.id.btnemail)
        Imghome=findViewById(R.id.imghome)




        Btncall.setOnClickListener {
            val phonenumber = "0772957098"
            val intent1= Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phonenumber"))
            startActivity(intent1)}
        Btnemail.setOnClickListener {

            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jjkyali5@gmail.com", null))

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")

            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")

            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        Imghome.setOnClickListener {
            val intent= Intent(this,HomepageActivity::class.java)
            startActivity(intent)
    }
} }