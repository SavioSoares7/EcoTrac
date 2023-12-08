package com.example.ecotrac

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecotrac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val inputSearch = binding.editSearch
        inputSearch.setTextColor(Color.BLACK)
    }
}