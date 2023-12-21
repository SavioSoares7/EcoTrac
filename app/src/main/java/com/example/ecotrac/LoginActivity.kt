package com.example.ecotrac

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecotrac.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        // Use o View Binding para inflar o layout
        // Defina a view inflada como o conteúdo da atividade
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textRegister = binding.textRegister

        val inputEmail = binding.inputEmail
        val inputPassword = binding.inputPassword
        val buttonToEnter = binding.buttonToEnter

        val buttonForgetPassword = binding.btnForgetPassword


        buttonForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        buttonToEnter.setOnClickListener{
            loginUser(inputEmail, inputPassword)
        }

        textRegister.setOnClickListener {
            toEnterScreenRegister()
        }
    }

    private fun toEnterScreenRegister() {
        val intent = Intent(this, singup::class.java)
        startActivity(intent)
    }

    private fun loginUser(emailField : EditText, passwordField : EditText){
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()

            emailField.setBackgroundResource(R.drawable.edit_linear_input_fail)
            passwordField.setBackgroundResource(R.drawable.edit_linear_input_fail)
            return
        }

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Conectado com sucesso", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Verifique seu email ou senha !!!", Toast.LENGTH_LONG).show()
                    }
            }
    }

}
