package com.atul.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_signup)

        var ml = findViewById<EditText>(R.id.mail)
        var unmm = findViewById<EditText>(R.id.upnm)
        var pwdd = findViewById<EditText>(R.id.upwd)

        val context = this

        var sin = findViewById<TextView>(R.id.signin)
        sin.setOnClickListener(){
            Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
            startActivity(Intent(this@Signup,MainActivity::class.java))

            var maupp = findViewById<Button>(R.id.btnsub)
            maupp.setOnClickListener(){
                Toast.makeText(this@Signup,"Gooooooo", Toast.LENGTH_LONG).show()
            }

        }
    }
}