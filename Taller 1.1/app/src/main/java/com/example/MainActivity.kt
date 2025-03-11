package com.example.taller11

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.taller11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idiomas = listOf("Español", "Inglés", "Francés", "Italiano", "Portugués", "Alemán")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIdiomas.adapter = adapter

        binding.btnTicTacToe.setOnClickListener {
            val intent = Intent(this, ActivityTicTacToe::class.java)
            startActivity(intent)
        }

        binding.btnRandomGreet.setOnClickListener{
            val idiomaSeleccionado = binding.spinnerIdiomas.selectedItem.toString()
            val intent = Intent(this, RandomGreetActivity::class.java)
            intent.putExtra("idioma", idiomaSeleccionado)
            startActivity(intent)
        }

        binding.btnPaises.setOnClickListener {
            val intent = Intent(this, PaisesActivity::class.java)
            startActivity(intent)
        }
    }
}