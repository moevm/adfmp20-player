package `class`

import android.util.Log



fun getPrettyTime(time: String): String{

    fun checkLength(value: Int, length:Int = 2):String {
        var output = value.toString();
        if (output.length == 1) {
            output = "0$output";
        }
        return output;
    }


    var newTime = time;

    if (newTime.isEmpty()) {
        Log.v("CheckEmptyString", "+");
    }

    val newTimeInt = newTime.toInt();

    newTime = "${checkLength(newTimeInt / 60)}:${checkLength(newTimeInt % 60)}";


    return newTime;
}


fun getPrettyTime(time: Int): String{
    return getPrettyTime(time.toString());
}