package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)


        buttonActive.setOnClickListener{
            Log.v("TEST","ClickButtonActive");

        }

        buttonPrev.setOnClickListener{
            Log.v("TEST","ClickButtonPrev");
        }

        buttonNext.setOnClickListener{
            Log.v("TEST","ClickButtonNext")
        }

        blockClosePlayer.setOnClickListener{
            Log.v("TEST","ClickBlockClosePlayer")
        }

        durationTrack.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    Log.v("TEST", "Change progress")
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    Log.v("TEST", "Touch Start")
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    Log.v("TEST", "Touch End ")
                }
            }
        )
    }

}
