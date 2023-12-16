package com.example.ecotrac

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.ecotrac.databinding.ActivityMainBinding
import com.example.ecotrac.databinding.ActivitySingupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import org.checkerframework.common.returnsreceiver.qual.This

class singup : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

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

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task -> if (task.isSuccessful){
                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show()
                toEnterScreenLogin()
            }else{
                Toast.makeText(this, "Cadastrado não realizado", Toast.LENGTH_LONG).show()
            }
        }
    }
}