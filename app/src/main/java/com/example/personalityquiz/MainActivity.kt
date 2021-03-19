package com.example.personalityquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "OnCreate: " + jsonString)
// parsing the string into our custom objects using Gson
        val gson = Gson()

        val jsonDataType = object : TypeToken<List<Question>>(){}.type
        val questions = gson.fromJson<List<Question>>(jsonString, jsonDataType)
        Log.d(TAG, "After: " + questions.toString())

        var quiz = Quiz(questions)

        textview_main_question.text = quiz.getCurrentQuestion().question
        button_main_option1.text = quiz.getCurrentQuestion().answers.elementAt(0).answer
        button_main_option2.text = quiz.getCurrentQuestion().answers.elementAt(1).answer
        button_main_nopreference.text = quiz.getCurrentQuestion().answers.elementAt(2).answer

            button_main_option1.setOnClickListener {
                if(quiz.hasNextQuestion()) {
                    quiz.updateScore(0)
                    textview_main_question.text = quiz.getNextQuestion().question
                    button_main_option1.text = quiz.getCurrentQuestion().answers.elementAt(0).answer
                    button_main_option2.text = quiz.getCurrentQuestion().answers.elementAt(1).answer
                    button_main_nopreference.text = quiz.getCurrentQuestion().answers.elementAt(2).answer
                }
                else {
                    button_main_option1.visibility = View.INVISIBLE
                    button_main_option2.visibility = View.INVISIBLE
                    textview_main_question.visibility = View.INVISIBLE
                    button_main_nopreference.visibility = View.INVISIBLE
                    textview_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_otherfinalscore.visibility = View.VISIBLE
                    textview_main_otherfinalscore.visibility = View.VISIBLE
                    textview_main_finalscore.text = quiz.showResults()
                    progressbar_main_finalscore.progress = quiz.finalScore
                    progressbar_main_otherfinalscore.progress = quiz.otherFinalScore
                    textview_main_otherfinalscore.text = "This is about how much you would prefer a PC!"
                }
            }
            button_main_option2.setOnClickListener {
                if(quiz.hasNextQuestion()) {
                    quiz.updateScore(1)
                    textview_main_question.text = quiz.getNextQuestion().question
                    button_main_option1.text = quiz.getCurrentQuestion().answers.elementAt(0).answer
                    button_main_option2.text = quiz.getCurrentQuestion().answers.elementAt(1).answer
                    button_main_nopreference.text = quiz.getCurrentQuestion().answers.elementAt(2).answer
                }
                else {
                    button_main_option1.visibility = View.INVISIBLE
                    button_main_option2.visibility = View.INVISIBLE
                    textview_main_question.visibility = View.INVISIBLE
                    button_main_nopreference.visibility = View.INVISIBLE
                    textview_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_otherfinalscore.visibility = View.VISIBLE
                    textview_main_otherfinalscore.visibility = View.VISIBLE
                    textview_main_finalscore.text = quiz.showResults()
                    progressbar_main_finalscore.progress = quiz.finalScore
                    progressbar_main_otherfinalscore.progress = quiz.otherFinalScore
                    textview_main_otherfinalscore.text = "This is about how much you would prefer a Mac!"
                }
            }
            button_main_nopreference.setOnClickListener {
                if(quiz.hasNextQuestion()) {
                    quiz.updateScore(2)
                    textview_main_question.text = quiz.getNextQuestion().question
                    button_main_option1.text = quiz.getCurrentQuestion().answers.elementAt(0).answer
                    button_main_option2.text = quiz.getCurrentQuestion().answers.elementAt(1).answer
                    button_main_nopreference.text = quiz.getCurrentQuestion().answers.elementAt(2).answer
                }
                else {
                    button_main_option1.visibility = View.INVISIBLE
                    button_main_option2.visibility = View.INVISIBLE
                    textview_main_question.visibility = View.INVISIBLE
                    button_main_nopreference.visibility = View.INVISIBLE
                    textview_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_finalscore.visibility = View.VISIBLE
                    progressbar_main_otherfinalscore.visibility = View.VISIBLE
                    textview_main_finalscore.text = quiz.showResults()
                    progressbar_main_finalscore.progress = quiz.finalScore
                }
            }
        // construct a quiz object
        // get the first question and set the text fields and buttons to match
        // in the button listeners, when the user clicks on something,
        // it passes the info on to the Quiz class. The quiz class decides if it's right or wrong and updates the score.
        // We check if there are more questions
        // If there are, we'll ask for another question and set that up
        // otherwise, we are done and go to the final score screen
    }
}