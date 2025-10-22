package com.example.proyectobase

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectobase.PCamara.CameraManager
import com.example.proyectobase.PCamara.CamaraUtils

class MainActivity7Canara : AppCompatActivity() {

    private var cameraManager: CameraManager? = null
    private lateinit var previewView: PreviewView
    private lateinit var contenedorFoto: ImageView
    private lateinit var btnTomarFoto: Button
    private lateinit var btnCargarFoto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_activity7_canara)

        btnTomarFoto = findViewById(R.id.btn_tomar_foto)
        btnCargarFoto = findViewById(R.id.btn_carga_foto)
        contenedorFoto = findViewById(R.id.imgv_foto)
        previewView = findViewById(R.id.previewView)

        btnTomarFoto.isEnabled = false

        // Permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            setupCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                101
            )
        }

        btnTomarFoto.setOnClickListener {
            cameraManager?.takePhoto { bitmap ->
                if (bitmap != null) {
                    contenedorFoto.setImageBitmap(bitmap)
                    val base64 = CamaraUtils.convertirDeBitMapABase64(bitmap)
                    Log.d("BASE64", base64.take(100) + "...")
                    Toast.makeText(this, "Foto capturada", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this, "Error al capturar", Toast.LENGTH_SHORT).show()
                }
            } ?: Toast.makeText(this, "Cámara no lista", Toast.LENGTH_SHORT).show()
        }

        btnCargarFoto.setOnClickListener {
            Toast.makeText(this, "Función de galería pendiente", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Se llama cuando se concede el permiso
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            setupCamera()
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCamera() {
        cameraManager = CameraManager(this)
        cameraManager?.startCamera(previewView)
        btnTomarFoto.isEnabled = true
    }
}
