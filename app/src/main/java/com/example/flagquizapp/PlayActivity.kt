package com.example.flagquizapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquizapp.databinding.ActivityPlayBinding


class PlayActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPlayBinding
    private val binding: ActivityPlayBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Do you want to go back to the main menu?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                finish()
                this.finish()
            }
            .setNegativeButton(
                "No"
            ) { dialog, _ -> //  Action for 'NO' Button
                dialog.cancel()
            }

        val alert = builder.create()
        alert.show()
    }
}