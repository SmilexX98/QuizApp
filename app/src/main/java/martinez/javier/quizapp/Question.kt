package martinez.javier.quizapp

import androidx.annotation.StringRes

data class Question (@StringRes val textResId:Int, val answer:Boolean)
