package com.example.taller11

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.taller11.databinding.ActivityTictactoeBinding

class ActivityTicTacToe : AppCompatActivity() {

    private lateinit var binding: ActivityTictactoeBinding
    private var turno = true
    private lateinit var botonesJuego: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTictactoeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botonesJuego = arrayOf(
            arrayOf(binding.boton1, binding.boton2, binding.boton3),
            arrayOf(binding.boton4, binding.boton5, binding.boton6),
            arrayOf(binding.boton7, binding.boton8, binding.boton9)
        )

        for (i in botonesJuego.indices) {
            for (j in botonesJuego[i].indices) {
                botonesJuego[i][j].setOnClickListener {
                    intentoJuego(botonesJuego[i][j], i, j)
                }
            }
        }

        binding.botonjuegonuevo.setOnClickListener {
            reiniciar()
        }

        indicadorTurno()
    }

    private fun intentoJuego(button: Button, row: Int, col: Int)
    {
        if (button.text.isNotEmpty()) return

        button.text = if (turno) "X" else "O"
        if (checkearGanador(row, col)) {
            binding.AvisoGanador.text = if (turno) "Ganas Jugador 1" else "Ganas Jugador 2"
            desactivarBotones()
        } else if (esEmpate()) {
            binding.AvisoGanador.text = "Empate"
        } else {
            turno = !turno
            indicadorTurno()
        }
    }

    private fun checkearGanador(row: Int, col: Int): Boolean
    {
        val jugado = if (turno) "X" else "O"

        //filas
        if (botonesJuego[row].all { it.text == jugado }) return true

        //columnas
        if (botonesJuego.all { it[col].text == jugado }) return true

        //diagonales
        if (row == col && botonesJuego.indices.all { botonesJuego[it][it].text == jugado }) return true

        //la otra diagonal
        if (row + col == 2 && botonesJuego.indices.all { botonesJuego[it][2 - it].text == jugado }) return true

        return false
    }

    private fun esEmpate(): Boolean
    {
        return botonesJuego.all { row -> row.all { it.text.isNotEmpty() } }
    }

    private fun desactivarBotones()
    {
        for (i in botonesJuego.indices)
        {
            for (j in botonesJuego[i].indices)
            {
                botonesJuego[i][j].isEnabled = false
            }
        }
    }

    private fun reiniciar()
    {
        for (i in botonesJuego.indices)
        {
            for (j in botonesJuego[i].indices)
            {
                botonesJuego[i][j].text = ""
                botonesJuego[i][j].isEnabled = true
            }
        }
        binding.AvisoGanador.text = ""
        turno = true
        indicadorTurno()
    }

    private fun indicadorTurno()
    {
        if (turno)
        {
            binding.jugador1texto.setTextColor(Color.RED)
            binding.jugador2texto.setTextColor(Color.BLACK)
        }
        else
        {
            binding.jugador1texto.setTextColor(Color.BLACK)
            binding.jugador2texto.setTextColor(Color.RED)
        }
    }
}