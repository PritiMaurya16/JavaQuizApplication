package com.example.javaquiz

import android.graphics.Color
import android.graphics.drawable.AdaptiveIconDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity(),View.OnClickListener {
    private var CurrentPosition = 1
    private var QuestionList: ArrayList<QuestionModel>? = null
    private var SelectedOptionPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        QuestionList = QuestionController.getQuestion()
        setQuestion()

        OptionOne.setOnClickListener(this)
        OptionTwo.setOnClickListener(this)
        OptiontThree.setOnClickListener(this)
        OptionFour.setOnClickListener(this)
        Submit.setOnClickListener(this)

    }

    fun setQuestion() {

        val Question: QuestionModel = QuestionList!![CurrentPosition - 1]
        optionDefaultBackground()
        if(CurrentPosition==QuestionList!!.size)
        {
            Submit.text="FINISH "
        }
        ProgressBar.progress = CurrentPosition
        ProgressReview.text = "$CurrentPosition/${QuestionList!!.size}"
        QuestionView.text = Question.QuestionText
        OptionOne.text = Question.OptionOne
        OptionTwo.text = Question.OptionTwo
        OptiontThree.text = Question.OptionThree
        OptionFour.text = Question.OptionFour
    }

    fun optionDefaultBackground() {
        val OptionsArrayList = ArrayList<TextView>()
        OptionsArrayList.add(OptionOne)
        OptionsArrayList.add(OptionTwo)
        OptionsArrayList.add(OptiontThree)
        OptionsArrayList.add(OptionFour)
        for (option in OptionsArrayList) {
            option.background = ContextCompat.getDrawable(this, R.drawable.backgroung_fill)
            option.setTextColor(Color.parseColor("#000000"))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.OptionOne -> {
                optionSelectedBackground(OptionOne, 1)
            }
            R.id.OptionTwo -> {
                optionSelectedBackground(OptionTwo, 2)
            }
            R.id.OptiontThree -> {
                optionSelectedBackground(OptiontThree, 3)
            }
            R.id.OptionFour -> {
                optionSelectedBackground(OptionFour, 3)
            }
            R.id.Submit -> {
                if (SelectedOptionPosition == 0) {
                    CurrentPosition++
                    when {
                        CurrentPosition <= QuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "Your Quiz is Completed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
                else {
                    val question = QuestionList!![CurrentPosition - 1]
                    if (question.CorrectAnswer != SelectedOptionPosition) {
                        redGreenHighlite(SelectedOptionPosition,R.drawable.wrong_answer)

                    }
                    redGreenHighlite(question.CorrectAnswer,R.drawable.correct_answer )
                    SelectedOptionPosition=0

                    }
                }

            }
        }
        fun redGreenHighlite(answer: Int, drawable: Int){
        when (answer) {
            1 -> {
                OptionOne.background = ContextCompat.getDrawable(this, drawable)
            }
            2 -> {
                OptionTwo.background = ContextCompat.getDrawable(this, drawable)
            }
            3 -> {
                OptiontThree.background = ContextCompat.getDrawable(this, drawable)
            }
            4 -> {
                OptionFour.background = ContextCompat.getDrawable(this, drawable)
            }

        }

    }
        fun optionSelectedBackground(Tv: TextView, selectedOptionNumber: Int) {
            optionDefaultBackground()
            SelectedOptionPosition = selectedOptionNumber
            Tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        }
    }
