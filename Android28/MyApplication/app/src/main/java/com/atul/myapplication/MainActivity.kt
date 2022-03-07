package com.atul.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sup = findViewById<TextView>(R.id.signupp)
        sup.setOnClickListener() {

            startActivity(Intent(this@MainActivity,signup::class.java))
        }

        var suin = findViewById<Button>(R.id.loginnn)
        suin.setOnClickListener() {
            Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_LONG).show()
        }

    }
}