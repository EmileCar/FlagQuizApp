package com.example.flagquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquizapp.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPlayBinding
    private val binding: ActivityPlayBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}