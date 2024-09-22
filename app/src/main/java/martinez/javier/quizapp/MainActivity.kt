package martinez.javier.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import martinez.javier.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.trueButton.setOnClickListener { view: View ->

            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->

            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {


            quizViewModel.moveToNext()


            updateQuestion()

        }

        binding.prevButton.setOnClickListener {

            quizViewModel.moveToPrev()
            updateQuestion()


        }



        updateQuestion()

    }

    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){

        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast

        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()

    }
}