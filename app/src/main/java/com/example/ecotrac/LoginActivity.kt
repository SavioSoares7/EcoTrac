package com.example.ecotrac

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecotrac.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use o View Binding para inflar o layout
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Defina a view inflada como o conteúdo da atividade

        val textRegister = binding.textRegister

        textRegister.setOnClickListener {
            toEnterScreenRegister()
        }
    }

    private fun toEnterScreenRegister() {
        val intent = Intent(this, singup::class.java)
        startActivity(intent)
    }
}
