package com.example.proyectobase.PCamara

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView

//clase camara manager: Controla las opciones de camara completas desde un solo package
class CamaraManager (private val context:Context){

    // almacenaremos en una variable el objeto imagen capturada
    val imageCapture:ImageCapture? = null

    /**
        funcion para inicializar la camara que recibe como parametro la vista previa
        de la camara
    */
    fun iniciarCamara(previewView: PreviewView){

        //crear una instancia de la camara
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        //inicializamos la variable anterior con un listener
        cameraProviderFuture.addListener(
            val cameraProvider = cameraProviderFuture.get() //inicializamos el elemento

            //variable para mantener la vista previa
            val preview = Preview.Builder().build().apply {

                //inicializador
                setSurfaceProvider(previewView.surfaceProvider)
            }



        )


    }
}