package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import audio.Audio
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val link:String = "https://i12.kissvk.com/api/song/download/get/10/Xassa-%D0%9C%D0%B0%D0%BB%D1%8E%D1%82%D0%BA%D0%B0-kissvk.com.mp3?origin=kissvk.com&url=sid%3A%2F%2F115080501_456239456_4b6bb7b29c47724683_c2f7bf4fcfa5dc12c3&artist=Xassa&title=%D0%9C%D0%B0%D0%BB%D1%8E%D1%82%D0%BA%D0%B0&index=0&future_urls=sid%3A%2F%2F115080501_456239454_774893715dc94b9e5c_63fc450172f3fcab24%2Csid%3A%2F%2F115080501_456239453_f7ed6f25e65c6401c9_85b73c625b2de4f27a%2Csid%3A%2F%2F115080501_456239452_22619796985a32f6b0_871d9aecf1934b110e%2Csid%3A%2F%2F115080501_456239451_91c5954a4f43cc1096_a6a998939a3429ba64%2Csid%3A%2F%2F115080501_456239450_39575cea6786fa15b3_ef534221322e1c7514%2Csid%3A%2F%2F115080501_456239449_dbf1304b21e169b757_514052eb5389318cef%2Csid%3A%2F%2F115080501_456239448_6403c039cd516441b3_6f460d0849b6effa14%2Csid%3A%2F%2F115080501_456239447_602149869e4a697d8d_f557df3000f0d29cb1%2Csid%3A%2F%2F115080501_456239446_885630ee3f0dbb82b3_e19629ea9f9607b32b";

        val mp:Audio = Audio();
        mp.set(link);
        mp.play()

        Run.after(4000) {
            if (false) {

            } else {
                println("2 seconds...")
                mp.pause();
            }
        }

        buttonOpen.setOnClickListener{
            //Api().getTrackListVk(115080501);
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }

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




