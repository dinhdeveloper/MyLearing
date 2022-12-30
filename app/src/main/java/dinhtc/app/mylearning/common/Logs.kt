package dinhtc.app.mylearning.common

import android.util.Log
import com.google.gson.Gson

object Logs {
    fun logText(textLog : String){
        Log.d("SSSSSSSSSS","$textLog")
    }

    fun logObject(objectModel : Object){
        Log.d("SSSSSSSSSS","${Gson().toJson(objectModel)}")
    }
}