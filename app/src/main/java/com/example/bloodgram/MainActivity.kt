package com.example.bloodgram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    lateinit var Txt_gotoreg: TextView
    lateinit var EdteMail: EditText
    lateinit var EdtPass: EditText
    lateinit var Btnlogin: Button
    lateinit var auth: FirebaseAuth
    lateinit var dbref: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Txt_gotoreg=findViewById(R.id.Tv_login)
        EdteMail=findViewById(R.id.EdtlogEmail)
        EdtPass=findViewById(R.id.EdtlogPass)
        Btnlogin=findViewById(R.id.btn_login)
        auth=FirebaseAuth.getInstance()
        dbref=FirebaseDatabase.getInstance()


        Txt_gotoreg.setOnClickListener {
            val intent= Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }
        Btnlogin.setOnClickListener {
            login()
        }
    }
    private fun login(){
        val email=EdteMail.text.toString()
        val pass=EdtPass.text.toString()

        if (email.isEmpty()||pass.isEmpty()){
            if (email.isEmpty()){
                EdteMail.error="Enter Your Email Address"
            }
            if (pass.isEmpty()){
                EdtPass.error="Enter Your Password"
            }
            Toast.makeText(this,"Enter valid details",Toast.LENGTH_LONG).show()
        }else if(pass.length !=8) {
            EdtPass.error = "Enter password with 8 characters or more"
            Toast.makeText(this, "Enter password with 8 characters or more", Toast.LENGTH_SHORT)
                .show()
        }else{
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                if (it.isSuccessful) {
                    val intent = Intent(this, HomepageActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Something went wrong,try again",Toast.LENGTH_LONG).show()

                }
            }
        }

    }
}

