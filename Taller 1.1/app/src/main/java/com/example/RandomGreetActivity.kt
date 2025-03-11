package com.example.taller11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller11.databinding.ActivityRandomGreetBinding

class RandomGreetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomGreetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomGreetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idiomaSeleccionado = intent.getStringExtra("idioma")
        val saludo = obtenerSaludo(idiomaSeleccionado ?: "Español")

        binding.btnSaludar.setOnClickListener {
            binding.textViewSaludo.text = saludo
        }
    }

    private fun obtenerSaludo(idioma: String): String {
        return when (idioma) {
            "Español" -> "Hola"
            "Inglés" -> "Hello"
            "Francés" -> "Bonjour"
            "Italiano" -> "Ciao"
            "Portugués" -> "Olá"
            "Alemán" -> "Hallo"
            else -> "Hola"
        }
    }
}