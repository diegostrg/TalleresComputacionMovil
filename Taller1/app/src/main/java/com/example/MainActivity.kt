package com.example.taller11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTicTacToe: Button = findViewById(R.id.btnTicTacToe)
        btnTicTacToe.setOnClickListener {
            val intent = Intent(this, ActivityTicTacToe::class.java)
            startActivity(intent)
        }
    }
}