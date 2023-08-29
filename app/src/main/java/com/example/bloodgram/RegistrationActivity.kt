package com.example.bloodgram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bloodgram.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {
    lateinit var Txt_login: TextView
    lateinit var Edtregusername:EditText
    lateinit var Edtregphone:EditText
    lateinit var Edtregbloodgroup:EditText
    lateinit var Edtregemail: EditText
    lateinit var Edtpass: EditText
    lateinit var Edtconfpass: EditText
    lateinit var Btnreg: Button
    lateinit var auth: FirebaseAuth
    lateinit var dbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)




        Edtregusername=findViewById(R.id.Edtusername)
        Edtregphone=findViewById(R.id.Edtphone)
        Edtregbloodgroup=findViewById(R.id.Edtbloodgroup)
        Txt_login=findViewById(R.id.Tv_log)
        Edtregemail=findViewById(R.id.EdtRegEmail)
        Edtpass=findViewById(R.id.EdtRegPass)
        Edtconfpass=findViewById(R.id.EdtconfPass)
        Btnreg=findViewById(R.id.btn_reg)
        auth= FirebaseAuth.getInstance()

        dbref=FirebaseDatabase.getInstance().getReference("users")

        Txt_login.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        Btnreg.setOnClickListener {
            Signupuser()

        }

    }
    private fun Signupuser(){
        val name = Edtregusername.text.toString()
        val bloodgroup = Edtregbloodgroup.text.toString()
        val email = Edtregemail.text.toString()
        val phone = Edtregphone.text.toString()
        val pass  = Edtpass.text.toString()
        val cpass = Edtconfpass.text.toString()

        if (name.isEmpty()||bloodgroup.isEmpty()||email.isEmpty()||phone.isEmpty()||pass.isEmpty()||cpass.isEmpty()){
            if (name.isEmpty()) {
                Edtregusername.error = "Enter Your Name"
            }
            if (bloodgroup.isEmpty()) {
                Edtregbloodgroup.error = "Enter Your Blood Type"
            }
            if (email.isEmpty()) {
                Edtregemail.error = "Enter Your Email"
            }
            if (phone.isEmpty()) {
                Edtregphone.error = "Enter Your Phone Number"
            }
            if (pass.isEmpty()) {
                Edtpass.error = "Enter Your Password"
            }
            if (cpass.isEmpty()) {
                Edtconfpass.error = "Confirm Your Password"
            }
            Toast.makeText(this,"Enter Valid Details",Toast.LENGTH_SHORT).show()




        }else if(phone.length !=10){
            Edtregphone.error="Enter valid phone number"
            Toast.makeText(this,"Enter valid phone number",Toast.LENGTH_SHORT).show()
        }else if(pass.length !=8) {
            Edtpass.error = "Enter password with 8 characters or more"
            Toast.makeText(this, "Enter password with 8 characters or more", Toast.LENGTH_SHORT)
                .show()
        }else if(pass !=cpass) {
            Edtconfpass.error = "Passwords do not match"
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                if (it.isSuccessful){
                  val databaseRef = dbref.ref.child("users").child(auth.currentUser!!.uid)
                  val users: Users = Users(name,bloodgroup,email,phone,auth.currentUser!!.uid)

                  databaseRef.setValue(users).addOnCompleteListener{
                      if (it.isSuccessful){
                          val intent=Intent(this,MainActivity::class.java)
                          startActivity(intent)
                      }else{
                          Toast.makeText(this,"Something went wrong,try again",Toast.LENGTH_LONG).show()
                      }
                  }
                }else{
                     Toast.makeText(this,"Something went wrong,try again",Toast.LENGTH_LONG).show()
                }
            }
        }



    }
}