package api

import android.util.Log
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import java.net.URL

class Api {

    fun getTrackListVk(id: Int, page: Int = 0) {

        Log.v("TEST", "test");

        doAsync {
            var test = URL("https://api-music.jenesius.com/api/songs/lists/$id/$page").readText()

            var songList =  Gson().fromJson(test, TrackList::class.java);

            Log.v("Test", test);

            for(elem in songList.songs) {

                Log.v("Test1", elem.id);
            }

        }

    }
}



class TrackList(val songs: List<Track>)

class Track(val id: String)