package com.example.proyectobase

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class
MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val btnCalculadora: Button = findViewById(R.id.btn_abrir_calculadora)

        //definimos un array
        val menuOpciones = listOf("Matemáticas", "Lenguaje", "Historia", "Inglés")

        //Buscamos el elemento lista del activity
        val lsMenu: ListView = findViewById(R.id.ls_mensajes)

        // Se crea un adaptador (ArrayAdapter) que sirve de puente
        // entre un array/lista de Strings (menuOpciones) y el ListView en pantalla.
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            menuOpciones
        )
        //se inicializa el elememento menu
        lsMenu.adapter = adapter

        lsMenu.setOnItemClickListener { _, _, position, _ ->
            val seleccionado = menuOpciones[position]
            Toast.makeText(this, "Elegiste: $seleccionado", Toast.LENGTH_SHORT).show()

            //se abre un activity segun la posicion
            if(seleccionado == menuOpciones[0]){
                val abrirCalculadora = Intent(this, MainActivity4::class.java)
                startActivity(abrirCalculadora)
            }
        }


        btnCalculadora.setOnClickListener{
            val abrirCalculadora = Intent(this, MainActivity4::class.java)
            startActivity(abrirCalculadora)

        }



        //ACTIVITY DESTINO
        val msjeBienvenida:TextView = findViewById(R.id.tx_bienvenido)
        //creo variable asigno valor recibido desde otro activity
        val usuarioDesdeOtroActivity = intent.getStringExtra("sesion")
        //seteo un TextView reemplazando el texto por el contenido.
        msjeBienvenida.text = usuarioDesdeOtroActivity.toString()

        val recibeContrasena = intent.getStringExtra("par_contrasena")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}