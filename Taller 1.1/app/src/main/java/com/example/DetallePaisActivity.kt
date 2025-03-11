package com.example.taller11

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taller11.databinding.ActivityDetallePaisBinding

class DetallePaisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePaisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibir los datos del intent
        val nombrePais = intent.getStringExtra("nombre_pais") ?: "Desconocido"
        val capital = intent.getStringExtra("capital") ?: "Desconocida"
        val sigla = intent.getStringExtra("sigla") ?: "--"

        // Mostrar los datos en la UI
        binding.textViewNombrePais.text = "País: $nombrePais"
        binding.textViewCapital.text = "Capital: $capital"
        binding.textViewSigla.text = "Código: $sigla"

        // Cargar la bandera según la sigla
        val banderaResId = resources.getIdentifier(sigla.lowercase(), "drawable", packageName)
        if (banderaResId != 0) {
            binding.imagenBandera.setImageResource(banderaResId)
        } else {
            binding.imagenBandera.visibility = View.GONE // Ocultar la imagen si no hay bandera
        }
    }
}
