package com.example.proyectobase.utils;

object OpMatematicas {
    fun sumar(a: Int, b: Int): Int {
        return a + b
    }

    fun restar(a: Int, b: Int): Int {
        return a - b
    }

    fun multiplicar(a: Int, b: Int): Int {
        return a * b
    }

    fun dividir(a: Int, b: Int): Int {
        return try {
            a / b
        } catch (e: ArithmeticException) {
            // Manejo si b = 0
            0
        } finally {
            // Aquí puedes poner código que siempre se ejecuta
            println("Intento de división: $a / $b")
        }
    }
}
