package com.example.proyectobase

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectobase.API.InsertarAlumnosAPI

class AcInsertApiAlumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_ac_insert_api_alumnos)

        val edNombre: EditText = findViewById(R.id.ed_nombre)
        val edApellido: EditText = findViewById(R.id.ed_apellido)
        val spGrupo: Spinner = findViewById(R.id.sp_grupo)
        val spSeccion: Spinner = findViewById(R.id.sp_seccion)
        val btnSave : Button = findViewById(R.id.btn_save)

        //lista para spinner grupos
        val grupos = listOf(
            "1"
            , "2"
            , "3"
            , "4"
            , "5"
            , "6"
            , "7"
            , "8"
            , "9"
            , "10"
            , "11"
            , "12"
            , "13"
            , "14"
            , "15"
        )

        val seccionesN = listOf("1"
            ,"2"
            ,"3"
            ,"4"
            ,"5"
            , "6"
            , "7"
            , "8"
            , "9"
            , "10")

        val adapter = ArrayAdapter(
            this
            , android.R.layout.simple_spinner_item, grupos).apply()
        {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        val adapter2 = ArrayAdapter(
            this
            , android.R.layout.simple_spinner_item, seccionesN).apply()
        {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spSeccion.adapter = adapter2
        spGrupo.adapter = adapter



        btnSave.setOnClickListener {
            InsertarAlumnosAPI.insertarAlumno(
                owner = this,
                context = this,
                nombre = edNombre.text.toString(),
                apellido = edApellido.text.toString(),
                grupo = spGrupo.selectedItem.toString(),
                seccion = spSeccion.selectedItem.toString(),
                archivoBytes = null,
                onSuccess = {
                    println("termino insert correcto")
                    Toast.makeText(this
                        , "guardado"
                        , Toast.LENGTH_SHORT)
                },
                onError = {
                    println("termino insert incorrecto")
                    Toast.makeText(this, "NO guardado", Toast.LENGTH_SHORT)
                }
            )
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}