package com.example.ecotrac

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.ecotrac.databinding.ActivityMainBinding
import com.example.ecotrac.databinding.ActivitySingupBinding

class singup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : ActivitySingupBinding = ActivitySingupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val textToEnter = binding.textToEnter
        val inputEmail = binding.inputEmail
        val inputPassword = binding.inputPassword
        val buttonRegister = binding.buttonRegister


        buttonRegister.setOnClickListener{
            registerUser(inputEmail,inputPassword)
        }

        textToEnter.setOnClickListener{
            toEnterScreenLogin()
        }

    }

    private fun toEnterScreenLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun registerUser(emailField : EditText, passwordField : EditText){
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()

            emailField.setBackgroundResource(R.drawable.edit_linear_input_fail)
            passwordField.setBackgroundResource(R.drawable.edit_linear_input_fail)
            return
        }

    }
}