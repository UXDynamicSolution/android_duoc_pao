package com.example.proyectobase.PCamara

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * Objeto Camara Utils
 * Convertira la imagen de formato BitMap a Base 64 para enviarla por APi
 */
object CamaraUtils {
    fun convertirDeBitMapABase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()

        //comprimimos nuestro parametro bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)

        //TOMAMOS EL ELEMENTO COMPRIMIDO Y LO TRANSFORMAMOS A Byte
        val byteArray = outputStream.toByteArray()
        //convertimos (codificamos el Byte Array q recibimos como parametro)
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)

    }
}