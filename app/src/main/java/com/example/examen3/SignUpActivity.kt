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

        val edtxResNombre = findViewById<EditText>(R.id.edtxResNombre)
        val edtxResContraseña = findViewById<EditText>(R.id.edtxResContraseña)
        val btnResRegistrarse = findViewById<Button>(R.id.btnResRegistrarse)

        val db = AppDatabase.getDatabase(this)

        btnResRegistrarse.setOnClickListener {
            val username = edtxResNombre.text.toString().trim()
            val password = edtxResContraseña.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val nuevoUsuario = Usuario(usuario = username, contraseña = password)

                    db.userDao().registrarUsuario(nuevoUsuario)

                    Toast.makeText(this@SignUpActivity, "¡Usuario registrado con éxito!", Toast.LENGTH_SHORT).show()
                    finish()

                } catch (e: Exception) {
                    Toast.makeText(this@SignUpActivity, "Error al registrar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}