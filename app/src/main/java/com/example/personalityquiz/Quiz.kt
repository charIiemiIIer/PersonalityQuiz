package com.example.personalityquiz

import kotlin.math.roundToInt

class Quiz(var questions: List<Question>) {

    var currentQuestion = 0 // takes the current question
    var chosenAnswer = questions[currentQuestion].answers
    var macScore = 0 // score you have if you choose an answer that pertains to the mac
    var pcScore = 0 // score you have if you choose an answer that pertains to a pc
    var allAnswers = questions[currentQuestion].answers
    var finalScore = 0
    var otherFinalScore = 0
    /**
     * @return: whether or not another question exists in the list of questions
     */
    fun hasNextQuestion() : Boolean {
        return currentQuestion < (questions.size - 1)
    }

    /**
     * @return the next question in the line of questions
     */
//    fun getNextQuestion() : Question? {
//        if(hasNextQuestion()) {
//            currentQuestion++
//            return questions[currentQuestion]
//        }
//        return null
//    }
    /**
     * Precondition: Another question exists
     */
    fun getNextQuestion() : Question {
        currentQuestion++
        return questions[currentQuestion]
    }

    fun getCurrentQuestion() : Question {
        return questions[currentQuestion]
    }

    fun updateScore(chosenAnswer : Int) {
        if(questions[currentQuestion].answers[chosenAnswer].mac == 1) // if they choose mac
            macScore += questions[currentQuestion].answers[chosenAnswer].mac
        else if(questions[currentQuestion].answers[chosenAnswer].pc == 1) // if they choose pc
            pcScore += questions[currentQuestion].answers[chosenAnswer].pc
    }

    fun showResults() : String {
        if(macScore > pcScore) {
            finalScore = (macScore / 15.0 * 100).roundToInt()
            otherFinalScore = 100 - finalScore

            return "You are most likely to use a Macintosh! Your answers indicate that you have a " + finalScore + "% chance to prefer a Mac!"

        }
        else if(pcScore > macScore) {
            finalScore = (pcScore / 15.0 * 100).roundToInt()
            otherFinalScore = 100 - finalScore
            return "You are most likely to use a Personal Computer! Your answers indicate that you have a " + finalScore + "% chance to prefer a PC!"
        }
        else {
            finalScore = 50
            return "You are equally as likely to prefer both a mac and a PC!"
        }
    }
}