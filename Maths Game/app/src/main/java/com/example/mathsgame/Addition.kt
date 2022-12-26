package com.example.mathsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addition.*
import java.util.*
import kotlin.random.Random

class Addition : AppCompatActivity() {

    var ans = 0
    var pointsScored = 0
    var lifeRemaining = 3

    lateinit var timer : CountDownTimer     // created the timer class object
    var startingTimerValue : Long = 60000   // in milli second
    var timeLeft : Long = startingTimerValue



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)


        // starting the game:
        nextButton.isClickable = false
        gameStart()

        okayButton.setOnClickListener {

            if (answer.text.toString() == "") {

                Toast.makeText(this, "Give answer to proceed", Toast.LENGTH_LONG).show()

            } else {

                nextButton.isClickable = true
                if(answer.text.toString().toInt() == ans){

                    question.text = "Congratulations. Correct answer."

                    pointsScored = pointsScored + 10
                    scoreVal.text = pointsScored.toString()

                } else{

                    pauseTimer()    // pausing the timer after the wrong ans

                    if (lifeRemaining == 0) {

                        question.text = "Game over with score $pointsScored"

                    } else {

                        lifeRemaining -= 1
                        question.text = "Wrong Answer. Try Again"

                    }

                    lifeVal.text = lifeRemaining.toString()
                }
            }
        }




        nextButton.setOnClickListener {

            pauseTimer()
            resetTimer()


            answer.text = null


            if (lifeRemaining == 0) {

                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()

                var intent = Intent(this@Addition, Congratuaation :: class.java)
                intent.putExtra("Score",pointsScored)
                startActivity(intent)
                finish()

            } else {
                gameStart()
            }
        }

    }

    fun gameStart() {

        nextButton.isClickable = false
        val num1 = Random.nextInt(0,1000)
        val num2 = Random.nextInt(0,1000)
        question.text = "$num1 + $num2"
        ans = num1 + num2

        // start tie timer:
        startTimer()
    }

    fun  startTimer() {

        // initializing the timer obj..as CountDownTimer is abstract class members are overriden:
        timer = object : CountDownTimer(timeLeft,1000) {

            override fun onTick(millisUntilFinished: Long) {

                // millisUntilFinished holds the current val of the timer

                timeLeft = millisUntilFinished

                updateTimeText()

            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateTimeText()


                question.text = "Sorry! time up"
                lifeRemaining -= 1
                lifeVal.text = lifeRemaining.toString()



            }

        }.start()
    }

    fun updateTimeText() {  // updates the timer text

        val showingValue : Int = (timeLeft / 1000).toInt()
        timeVal.text = String.format(Locale.getDefault() , "%02d", showingValue)  //formatted
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {

        timeLeft = startingTimerValue
        updateTimeText()
    }
}