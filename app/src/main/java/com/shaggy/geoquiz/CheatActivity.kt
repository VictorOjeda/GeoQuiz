package com.shaggy.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
private const val KEY_IS_ANSWERED = "isAnswered"
private const val TAG = "CheatActivity"

class CheatActivity : AppCompatActivity() {
    private lateinit var showAnswerButton: Button
    private lateinit var answerTextView: TextView

    private var answerIsTrue = false
    private var isAnswerShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        isAnswerShown = savedInstanceState?.getBoolean(KEY_IS_ANSWERED) ?: false
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        showAnswerButton = findViewById(R.id.show_answer_button)
        answerTextView = findViewById(R.id.answer_text_view)

        showAnswerButton.setOnClickListener {
            updateAnswerText()
        }

        if (isAnswerShown) {
            updateAnswerText()
        }
    }

    private fun updateAnswerText() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        answerTextView.setText(answerText)
        setAnswerShownResult(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "saving state isAnswerShown: $isAnswerShown")
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_ANSWERED, isAnswerShown)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        Log.d(TAG, "setting isAnswerShown: $isAnswerShown")
        this.isAnswerShown = isAnswerShown
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}