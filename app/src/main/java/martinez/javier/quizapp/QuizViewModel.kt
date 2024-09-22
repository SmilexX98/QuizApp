package martinez.javier.quizapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


private const val TAG = "DesdeViewModel"

private val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"


class QuizViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {


    private val questionBank = listOf(
        Question(R.string.question_champions_ganador,true),
        Question(R.string.question_lugar_Final,false),
        Question(R.string.question_empezo,true),
        Question(R.string.question_formato,false),
        Question(R.string.question_cuando_Final,true)
        //falta mas preguntas
    )

    private var currentIndex:Int

        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY,value)

    val currentQuestionAnswer:Boolean

        get()=questionBank[currentIndex].answer

    val currentQuestionText:Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrev(){
        currentIndex = if (currentIndex == 0){
            questionBank.size - 1
        }else
            (currentIndex - 1) % questionBank.size
    }


}