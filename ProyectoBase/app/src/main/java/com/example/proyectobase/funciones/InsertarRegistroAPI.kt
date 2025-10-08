package com.example.proyectobase.funciones

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyectobase.API.InsertarAlumnosAPI
import com.example.proyectobase.DB.AlumnosLocalRepository
import com.example.proyectobase.R
import kotlinx.coroutines.launch

class InsertarRegistroAPI : AppCompatActivity() {

    // ahora son propiedades de clase (no locales)
    private lateinit var edNombre: EditText
    private lateinit var edApellido: EditText
    private lateinit var edGrupo: EditText
    private lateinit var spSeccion: Spinner
    private lateinit var btnInsertar: Button
    private lateinit var btnInsertarSQLite: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertar_registro_api)

        // referencias
        edNombre = findViewById(R.id.ed_nombre_api)
        edApellido = findViewById(R.id.ed_apellido_api)
        edGrupo   = findViewById(R.id.ed_grupo_api)
        spSeccion = findViewById(R.id.sp_seccion)
        btnInsertar = findViewById(R.id.btn_insertar)
        btnInsertarSQLite = findViewById(R.id.btn_insert_sqliteee) // tu id

        // Spinner (ejemplo)
        val secciones = listOf("1", "2", "3", "4")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, secciones).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spSeccion.adapter = adapter

        // listeners
        btnInsertar.setOnClickListener { onInsertApiClick() }
        btnInsertarSQLite.setOnClickListener { onInsertSQLiteClick() }
    }

    private fun leerFormulario(): Quadruple<String, String, String, String>? {
        val nombre  = edNombre.text.toString().trim()
        val apellido= edApellido.text.toString().trim()
        val grupo   = edGrupo.text.toString().trim()
        val seccion = spSeccion.selectedItem?.toString()?.trim().orEmpty()

        if (nombre.isEmpty() || apellido.isEmpty() || grupo.isEmpty() || seccion.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return null
        }
        return Quadruple(nombre, apellido, grupo, seccion)
    }

    // Botón 1: INSERT a la API
    private fun onInsertApiClick() {
        val data = leerFormulario() ?: return
        val (nombre, apellido, grupo, seccion) = data

        btnInsertar.isEnabled = false

        InsertarAlumnosAPI.insertarAlumno(
            owner = this,
            context = this,
            nombre = nombre,
            apellido = apellido,
            grupo = grupo,
            seccion = seccion,
            archivoBytes = null,
            onSuccess = {
                limpiarFormulario()
                btnInsertar.isEnabled = true
            },
            onError = {
                btnInsertar.isEnabled = true
            }
        )
    }

    // Botón 2: Guardar en SQLite (asumiendo que ya tienes AlumnosLocalRepository del ejemplo anterior)
    private fun onInsertSQLiteClick() {
        val data = leerFormulario() ?: return
        val (nombre, apellido, grupo, seccion) = data

        btnInsertarSQLite.isEnabled = false

        lifecycleScope.launch {
            val res = AlumnosLocalRepository.insert(
                context = this@InsertarRegistroAPI,
                nombre = nombre,
                apellido = apellido,
                grupo = grupo,
                seccion = seccion
            )
            res.onSuccess { rowId ->
                Toast.makeText(this@InsertarRegistroAPI, "Guardado local (id=$rowId)", Toast.LENGTH_SHORT).show()
                limpiarFormulario()
            }.onFailure { e ->
                Toast.makeText(this@InsertarRegistroAPI, "Error SQLite: ${e.message}", Toast.LENGTH_LONG).show()
            }
            btnInsertarSQLite.isEnabled = true
        }
    }

    private fun limpiarFormulario() {
        edNombre.text?.clear()
        edApellido.text?.clear()
        edGrupo.text?.clear()
        spSeccion.setSelection(0)
    }
}

/** Pequeño helper para retornar 4 valores sin crear data class */
data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
