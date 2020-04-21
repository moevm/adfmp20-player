package com.example.myapplication


import `class`.StorageVK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import audio.Audio
import audio.TrackList
import com.google.gson.Gson
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint
import kotlinx.android.synthetic.main.activity_error_auth.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import java.net.URL


class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        println("HYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        println( getCertificateFingerprint(this, this.packageName)?.toList().toString())
        println("HYYYYYYYYYYYYYYYYYYYYYYYYYYYY");


        setContentView(R.layout.activity_splash_screen);

        StorageVK.setSP(getSharedPreferences("VK", Context.MODE_PRIVATE));



        //StorageVK.setId(0);





    }
    override fun onStart() {



        val test = {
            AlertDialog.Builder(this)
                .setMessage("Please, confirm the action")
                .setPositiveButton("Confirm") { dialog, which ->
                    // handle click
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }


        super.onStart()

        val vkId:Int = StorageVK.getId();

        println("ELEM $vkId");

        if (vkId == 0) {

            openVkActivity();
        } else {


            doAsync {

                val URL = "https://api-music.jenesius.com/api/songs/lists/v2/$vkId/0";

                val httpClient = OkHttpClient()
                val request = Request.Builder()
                    .url(URL)
                    .build()


                val response = httpClient.newCall(request).execute()

                println("+!!");

                if (response.isSuccessful) {
                    val body = response.body?.string()

                    val songList =  Gson().fromJson(body, TrackList::class.java);

                    Audio.setList(songList.songs);

                    runOnUiThread {
                        openVkActivity();
                    }
                } else {
                    runOnUiThread {
                        //test();
                        openErrorAuthPage();
                    }


                }


            }
        }

    }
    private fun openVkActivity(){

        val vkPage = Intent(this, MainActivity::class.java);
        startActivity(vkPage);
    }

    private fun openErrorAuthPage(){



        val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_error_auth, null);

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .create()

        mBuilder.show()



        mDialogView.closeErrorPopup.setOnClickListener {
            runOnUiThread {
                StorageVK.setId(0);
                openVkActivity();
            }
            mBuilder.dismiss()
        }




    }




}



