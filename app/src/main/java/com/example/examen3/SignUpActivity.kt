package com.example.examen3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.examen3.db.AppDatabase
import com.example.examen3.model.Usuario
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Enlazamos tus componentes usando tus IDs exactos del XML
        val edtxResNombre = findViewById<EditText>(R.id.edtxResNombre)
        val edtxResContraseña = findViewById<EditText>(R.id.edtxResContraseña)
        val btnResRegistrarse = findViewById<Button>(R.id.btnResRegistrarse)

        // Obtenemos la instancia de la base de datos
        val db = AppDatabase.getDatabase(this)

        btnResRegistrarse.setOnClickListener {
            val username = edtxResNombre.text.toString().trim()
            val password = edtxResContraseña.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ejecutamos la inserción usando Corrutinas
            lifecycleScope.launch {
                try {
                    // Creamos el objeto Usuario (usando la clase del proyecto)
                    val nuevoUsuario = Usuario(usuario = username, contrasenia = password)

                    // Inserción limpia a través del DAO
                    db.userDao().registrarUsuario(nuevoUsuario)

                    Toast.makeText(this@SignUpActivity, "¡Usuario registrado con éxito!", Toast.LENGTH_SHORT).show()
                    finish() // Regresa a la pantalla principal

                } catch (e: Exception) {
                    Toast.makeText(this@SignUpActivity, "Error al registrar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}