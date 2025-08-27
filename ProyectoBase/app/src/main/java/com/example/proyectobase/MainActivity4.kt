package com.example.proyectobase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Importamos archivos del package utils creado
 */
import com.example.proyectobase.utils.OpMatematicas


class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        val numeroUno: EditText = findViewById(R.id.ed_opUno)
        val numeroDos: EditText = findViewById(R.id.ed_opDos)
        val txResultado: TextView = findViewById(R.id.tx_resultado_act4)
        val btnCalcular: Button = findViewById(R.id.btn_calcular)

        //test funcion
        val resultado = OpMatematicas.sumar(5, 7)
        println("La suma es: $resultado")

        btnCalcular.setOnClickListener {
            var valorUno: Int = numeroUno.text.toString().toIntOrNull() ?: 0
            var valorDos: Int = numeroDos.text.toString().toIntOrNull() ?: 0

            var resultado = OpMatematicas.dividir(valorUno, valorDos)

            txResultado.text = resultado.toString()
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

/**
 * val db = DbConn(this)
 *
 * // INSERT
 * val id = db.insertUser(User(name = "Carlos", email = "carlos@correo.com"))
 *
 * // SELECT
 * val user = db.getUserById(id)
 * println("Usuario: $user")
 *
 * // UPDATE
 * db.updateUser(id, "Carlos Muñoz", "cmunoz@correo.com")
 *
 * // SELECT ALL
 * val users = db.getAllUsers()
 * users.forEach { println(it) }
 *
 * // DELETE
 * db.deleteUser(id)
 *
 */