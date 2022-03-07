package com.atul.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var ml = findViewById<EditText>(R.id.mail)
        var unmm = findViewById<EditText>(R.id.upnm)
        var pwdd = findViewById<EditText>(R.id.upwd)

        val context = this

        var sin = findViewById<TextView>(R.id.signin)
        sin.setOnClickListener(){
            Toast.makeText(context,"Hello", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@signup,MainActivity::class.java))

            var subb = findViewById(R.id.btnsub) as Button
            subb.setOnClickListener(){
                Toast.makeText(context,"Hello", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@signup,MainActivity::class.java))
            }

        }

    }
}