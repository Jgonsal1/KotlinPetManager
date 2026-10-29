package com.example.kotlinpetmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotlinpetmanager.model.Paciente

class PacienteViewModel : ViewModel() {

    // Lista observable: solo el ViewModel puede modificarla
    var pacientes by mutableStateOf<List<Paciente>>(emptyList())
        private set

    // Estado del formulario
    var nombre by mutableStateOf("")
        private set
    var especie by mutableStateOf("")
        private set
    var edad by mutableStateOf("")
        private set
    var contacto by mutableStateOf("")
        private set
    var esUrgencia by mutableStateOf(false)
        private set

    // Mensaje de error de validación (null = sin error)
    var error by mutableStateOf<String?>(null)
        private set

    private var siguienteId = 1

    fun onNombreChange(valor: String) { nombre = valor }
    fun onEspecieChange(valor: String) { especie = valor }
    fun onEdadChange(valor: String) {
        // Solo permite dígitos
        if (valor.all { it.isDigit() }) edad = valor
    }
    fun onContactoChange(valor: String) { contacto = valor }
    fun onUrgenciaChange(valor: Boolean) { esUrgencia = valor }

    fun agregarPaciente() {
        val edadInt = edad.toIntOrNull()

        when {
            nombre.isBlank() -> error = "El nombre es obligatorio"
            especie.isBlank() -> error = "La especie/raza es obligatoria"
            edadInt == null -> error = "Ingresa una edad válida"
            contacto.isBlank() -> error = "El contacto de emergencia es obligatorio"
            else -> {
                val nuevo = Paciente(
                    id = siguienteId++,
                    nombre = nombre.trim(),
                    especie = especie.trim(),
                    edad = edadInt,
                    contacto = contacto.trim(),
                    esUrgencia = esUrgencia
                )
                pacientes = pacientes + nuevo   // crea una lista nueva para que Compose detecte el cambio
                limpiarFormulario()
            }
        }
    }

    fun limpiarFormulario() {
        nombre = ""
        especie = ""
        edad = ""
        contacto = ""
        esUrgencia = false
        error = null
    }
}