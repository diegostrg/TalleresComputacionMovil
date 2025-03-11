package com.example.taller11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller11.databinding.ActivityPaisesBinding
import org.json.JSONObject
import java.io.IOException

class PaisesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaisesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Leer el JSON desde assets
        val jsonString = leerJSONDesdeAssets("paises.json")
        val listaPaises = extraerListaPaises(jsonString)

        // 2. Configurar el ListView con el adaptador personalizado
        val adapter = PaisAdapter(this, listaPaises)
        binding.listViewPaises.adapter = adapter

        // 3. Manejar clics en los países para abrir DetallePaisActivity
        binding.listViewPaises.setOnItemClickListener { _, _, position, _ ->
            val paisSeleccionado = listaPaises[position]
            val intent = Intent(this, DetallePaisActivity::class.java).apply {
                putExtra("nombre_pais", paisSeleccionado.nombre)
                putExtra("capital", paisSeleccionado.capital)
                putExtra("sigla", paisSeleccionado.sigla)
            }
            startActivity(intent)
        }
    }

    // Función para leer el JSON desde la carpeta assets
    private fun leerJSONDesdeAssets(nombreArchivo: String): String {
        return try {
            val inputStream = assets.open(nombreArchivo)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    // Extraer los datos de los países desde el JSON
    private fun extraerListaPaises(jsonString: String): List<Pais> {
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("paises")

        val listaPaises = mutableListOf<Pais>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val pais = Pais(
                nombre = obj.getString("nombre_pais"),
                capital = obj.getString("capital"),
                sigla = obj.getString("sigla")
            )
            listaPaises.add(pais)
        }
        return listaPaises
    }
}
