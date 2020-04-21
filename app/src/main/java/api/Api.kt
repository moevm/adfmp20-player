/*package api

import android.util.Log

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import java.net.URL

object Api {


    fun getTrackListVk(id: Int, page: Int = 0) {

        Log.v("TEST", "+0");

         doAsync {

            Log.v("TEST", "+1");
            var test = URL("https://api-music.jenesius.com/api/songs/lists/v2/$id/$page").readText()

            var songList =  Gson().fromJson(test, TrackList::class.java);

            Log.v("Test", test);

            for(elem in songList.songs) {

                Log.v("Test1", elem.index);
            }


        }

        Log.v("TEST", "+2");

    }
}

*/