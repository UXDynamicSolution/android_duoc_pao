package com.example.proyectobase.API

import android.content.Context
import android.util.Base64
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

object InsertarAlumnosAPI {

    /**
     * Inserta un alumno usando el Repository. Muestra Toasts y permite callbacks.
     * @param owner Un LifecycleOwner (p.ej. la Activity)
     * @param context Context para Toasts
     * @param nombre / apellido / grupo / seccion Datos del formulario
     * @param archivoBytes (opcional) si quieres enviar binario real; si es null, manda demo
     * @param onSuccess callback con el mensaje del backend
     * @param onError callback con el Throwable en caso de error
     */
    fun insertarAlumno(
        owner: LifecycleOwner,
        context: Context,
        nombre: String,
        apellido: String,
        grupo: String,
        seccion: String,
        archivoBytes: ByteArray? = null,
        onSuccess: ((String?) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        // Base64 del archivo (demo si no se entrega)
        val archivoBase64 = if (archivoBytes != null) {
            Base64.encodeToString(archivoBytes, Base64.NO_WRAP)
        } else {
            Base64.encodeToString("BinarioDeEjemplo".toByteArray(), Base64.NO_WRAP)
        }

        val request = AlumnoInsertRequest(
            nombre = nombre.trim(),
            apellido = apellido.trim(),
            grupo = grupo.trim(),
            seccion = seccion.trim(),
            archivo = archivoBase64
        )

        owner.lifecycleScope.launch {
            val res = AlumnosRepository.insertAlumno(request)
            res.onSuccess { r ->
                Toast.makeText(context, r.message ?: "Insert OK", Toast.LENGTH_LONG).show()
                onSuccess?.invoke(r.message)
            }.onFailure { e ->
                Toast.makeText(context, "Error insertando: ${e.message}", Toast.LENGTH_LONG).show()
                onError?.invoke(e)
            }
        }
    }
}
