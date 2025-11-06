package com.example.proyectobase

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.proyectobase.funciones.ValidarConexionWAN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //funcion heredada para verificar conexion a internet
        if(ValidarConexionWAN.isOnline(this)){
            println("conectado")
        }else{
            println("sin conexion")
            val toast = Toast.makeText(
                this
                , "SIN CONEXION"
                , Toast.LENGTH_SHORT).show()
        }

        //inicializamos variables de elementos layout
        val edUsername:EditText = findViewById(R.id.ed_username)
        val edPasswd:EditText = findViewById(R.id.ed_password)
        val btnLogin:Button = findViewById(R.id.btn_login)
        val txMensaje:TextView = findViewById(R.id.tx_mensaje)

        // variables para comparar password
        var usuarioBase = "admin"
        var passwBase = "admin"

        var valUsername:Boolean = false
        var valPassword:Boolean = false

        //boton por defecto desactivado
        btnLogin.isEnabled = false

        /***
         * validaciones (en proceso)
         * - evento addTextChargedListener
         * -- revisar con banderas comportamiento
         */
        edUsername.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                valUsername = true

            }

        }

        )

        edPasswd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                btnLogin.isEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {
                if(valUsername == true && edUsername.text.toString() != ""){
                    valPassword = true
                }

                if(valUsername == true && valPassword == true){
                    btnLogin.isEnabled = true
                }

            }

        }

        )

        btnLogin.setOnClickListener {

            if(edUsername.text.toString() == usuarioBase
                && edPasswd.text.toString() == passwBase){

                // creo un objeto intent
                val nuevaVentana = Intent(this, AcInsertApiAlumnos::class.java)
                var ses_username = edUsername.text.toString()

                nuevaVentana.putExtra("sesion",ses_username )
                nuevaVentana.putExtra("par_contrasena", edPasswd.text.toString() )                //abrimos el activity
                startActivity(nuevaVentana)

                val toast = Toast.makeText(this, "Bienvenid@s: "+ses_username, Toast.LENGTH_SHORT) // in Activity
                toast.show()
                txMensaje.text = "login OK"
            }else{
                txMensaje.text = "login NO"
                btnLogin.isEnabled = false
                edUsername.setText("")
                edPasswd.setText("")
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}