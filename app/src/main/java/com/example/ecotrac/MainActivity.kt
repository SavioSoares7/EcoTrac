package com.example.ecotrac

import android.graphics.Color

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecotrac.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputPost = binding.inputPost
        val btnPost = binding.btnAdd
        val listView: ListView = binding.listView

        val database = FirebaseDatabase.getInstance()
        val postsDatabase = database.getReference("post")

        val postList = mutableListOf<String>()
        val adapter = ArrayAdapter(this, R.layout.list_item_layout, postList)
        listView.adapter = adapter

        btnPost.setOnClickListener {
            btnAddPost(inputPost)
        }

        postsDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postList.clear()

                for (postSnapshot in dataSnapshot.children) {
                    val post = postSnapshot.child("Post").getValue(String::class.java)
                    val datePost = postSnapshot.child("DataPublicacao").getValue(String::class.java)
                    post?.let {
                        postList.add("$it - Publicado em: $datePost")
                    }
                }

                // Notifica o adaptador sobre as mudanças na lista
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: com.google.firebase.database.DatabaseError) {
                // Lidar com erros de leitura do Firebase
                Toast.makeText(this@MainActivity, "Erro no Firebase: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun btnAddPost(inputPost: EditText) {

        val database = FirebaseDatabase.getInstance()
        val postDatabase = database.getReference("post")
        val post = inputPost.text.toString()

        if (post.isEmpty()) {
            inputPost.setBackgroundResource(R.drawable.edit_linear_input_fail)
            Toast.makeText(this, "Escreva algo para postar", Toast.LENGTH_LONG).show()
            return
        }

        // Cria uma nova chave única para o post
        val postKey = postDatabase.push().key

        // Cria um mapa de dados
        val data = HashMap<String, Any>()
        data["Post"] = post
        data["DataPublicacao"] = getCurrentDate()

        // Insere os dados com a chave única
        postKey?.let {
            postDatabase.child(it).setValue(data).addOnSuccessListener {
                Toast.makeText(this, "Publicado", Toast.LENGTH_LONG).show()
                inputPost.setText("") // Limpa o campo de entrada após a postagem
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao inserir os dados", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getCurrentDate(): String{
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        val date = Date()
        return  dateFormat.format(date)
    }
}