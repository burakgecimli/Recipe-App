package com.example.sisterslabprojectrecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sisterslabprojectrecipes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
