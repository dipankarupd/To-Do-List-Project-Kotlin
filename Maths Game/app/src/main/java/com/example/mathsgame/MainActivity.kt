package com.example.mathsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add.setOnClickListener {
            addition()
        }
        multipy.setOnClickListener {
            multiplication()
        }
        subtract.setOnClickListener {
            subtraction()
        }
    }

    private fun subtraction() {

        var intent = Intent(this@MainActivity , Subtraction::class.java)
        startActivity(intent)
    }

    private fun multiplication() {

        var intent = Intent(this@MainActivity , Multiplication::class.java)
        startActivity(intent)
    }

    private fun addition() {
        var intent = Intent(this@MainActivity , Addition::class.java)
        startActivity(intent)
    }
}