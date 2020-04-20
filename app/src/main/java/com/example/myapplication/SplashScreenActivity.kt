package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import audio.Audio


import audio.TrackList
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class SplashScreenActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen);


        pref = getSharedPreferences("VK_ID", Context.MODE_PRIVATE);


        var vkId: Int = 0;

        val editor = pref.edit();
        editor.putInt("VK_ID", 115080501);
        editor.apply();


        if (pref.contains("VK_ID")) {
            vkId = pref.getInt("VK_ID", 0);
            Log.v("Test", vkId.toString());

        }



        if (vkId == 0) {
            val vkPage = Intent(this, MainActivity::class.java);
            startActivity(vkPage);
        } else {

            doAsync {

                Log.v("TEST", "+1");
                var test = URL("https://api-music.jenesius.com/api/songs/lists/v2/$vkId/0").readText()

                var songList =  Gson().fromJson(test, TrackList::class.java);



                Audio.setList(songList.songs);



                uiThread {
                    openVkActivity();
                }

            }
        }


    }
    fun openVkActivity(){
        val vkPage = Intent(this, MainActivity::class.java);
        startActivity(vkPage);
    }



}



