package com.example.myapplication

import `class`.getPrettyTime
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.SeekBar
import audio.Audio
import bus.Bus
import kotlinx.android.synthetic.main.activity_player.*
import java.util.*
import kotlin.concurrent.schedule


class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)


        val checkInfoTrack = {
            titleTrack.text = Audio.currentElem().title;
            artistTrack.text = Audio.currentElem().artist;
            durationTrack.text = getPrettyTime(Audio.currentElem().duration);
        }
        Bus.on("changeTrackPlayer", checkInfoTrack);
        checkInfoTrack();


        val changeActiveButton = {
            if(Audio.isActive())
                buttonActive.setBackgroundResource(R.drawable.pause);
            else
                buttonActive.setBackgroundResource(R.drawable.play);
        }
        Bus.on("changeActivePlayer", changeActiveButton);
        changeActiveButton();


        buttonActive.setOnClickListener{
            Audio.toggle();

        }

        buttonPrev.setOnClickListener{
            Audio.prev();
        }

        buttonNext.setOnClickListener{
            Audio.next();
        }

        blockClosePlayer.setOnClickListener{
            Log.v("TEST","ClickBlockClosePlayer")

            finish();
        }
        loopButton.setOnClickListener {
            Audio.loop();
        }


        seekBarPlayer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar


            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                Log.v("Progress", "+")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {


                Audio.setCurrentTimeByProgress(seekBar.progress);
            }
        })

        val updateSeekBar = {
            //currentDuration.text = "123";

            Log.v("123", "1");

        }

        val thread = Thread {
            Timer().schedule(1000, 1000) {

                var msg = Message();
                msg.what = 123;

                handler.sendMessage(msg);
            }
        }
        thread.start()

    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg);

            currentDuration.text = getPrettyTime(Audio.currentDuration().toString());

            var progressDuration = (Audio.currentDuration().toFloat() / Audio.durationTrack() * 100).toInt()
            seekBarPlayer.progress = progressDuration;


        }
    }


}
