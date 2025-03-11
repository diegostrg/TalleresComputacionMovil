package com.example.taller11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import android.widget.BaseAdapter

class PaisAdapter(private val context: Context, private val paises: List<Pais>) : BaseAdapter() {

    override fun getCount(): Int = paises.size
    override fun getItem(position: Int): Any = paises[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_pais, parent, false)

        val pais = paises[position]

        val imagenBandera = view.findViewById<ImageView>(R.id.imagenBandera)
        val textViewNombrePais = view.findViewById<TextView>(R.id.textViewNombrePais)
        val textViewCapital = view.findViewById<TextView>(R.id.textViewCapital)

        // Configurar nombre y capital
        textViewNombrePais.text = pais.nombre
        textViewCapital.text = "Capital: ${pais.capital} - Código: ${pais.sigla}"

        // Cargar imagen de la bandera
        val banderaResId = context.resources.getIdentifier(pais.sigla.lowercase(), "drawable", context.packageName)
        if (banderaResId != 0) {
            imagenBandera.setImageResource(banderaResId)
        } else {
            imagenBandera.setImageResource(R.drawable.ic_bandera_default) // Imagen por defecto si no se encuentra la bandera
        }

        return view
    }
}
