package com.example.mathsgame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_congratuaation.*

class Congratuaation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratuaation)

        var ans = intent.getIntExtra("Score" , 0)
        var text : String = score.text as String

        score.text = text + ans.toString()

        playAgainBtn.setOnClickListener {

            var intent = Intent(this@Congratuaation , MainActivity :: class.java)
            startActivity(intent)
            finish()
        }

        exitBtn.setOnClickListener {

            showAlertDialog()
        }
    }

    private fun showAlertDialog() {

        var alert = AlertDialog.Builder(this@Congratuaation)
        alert.setTitle("Exit")
            .setMessage("Are You sure you want to exit? ")
            .setCancelable(false)
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setNegativeButton("No", DialogInterface.OnClickListener
            { dialogInterface, which->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                    exitApp()
            })
        alert.show()
    }

    private fun exitApp() {
        var intent = Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()

    }


}