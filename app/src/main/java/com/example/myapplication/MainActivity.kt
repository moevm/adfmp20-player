package com.example.myapplication


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* val mp:Audio = Audio();
        mp.set(link);
        mp.play()

        Run.after(4000) {
            if (false) {

            } else {
                println("2 seconds...")
                mp.pause();
            }
        }*/

       /* buttonOpen.setOnClickListener{
            //Api().getTrackListVk(115080501);

            /*val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)*/

            val intent = Intent(this, VkNoAuthActivity::class.java)
            startActivity(intent);

        }*/

    }
}
class Run {
    companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler().postDelayed({
                process()
            }, delay)
        }
    }
}




