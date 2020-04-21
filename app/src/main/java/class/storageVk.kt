package `class`

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import audio.Track


/**
 * Класс для хранения информации о юзере ВК
 *
 * private:
 * id пользователя - если пользователь не вошёл то значение -1, иначе его id
 * songList - MutableList хранящий список треков
 *
 * Для случайного не изменения
 * setId
 * getId
 *
 * addSongList
 * clearSongList
 * getSongList
 *
 * clear - удаляет всю информцию о пользователя включая песни и id
 *
 * */

object StorageVK{


    private var id: Int = 0;
    private lateinit var songList: MutableList<Track>;
    private lateinit var preferences: SharedPreferences;

    private var NAME_ID = "VK_ID";

    fun setId(id:Int) {


        this.id = id;
        this.preferences.edit().putInt(NAME_ID, id).apply();

    }
    fun getId(): Int {
        return this.preferences.getInt(NAME_ID, 0);
    }


    public fun addSongList(list: List<Track>) {
        songList.addAll(list);
    }
    public fun clearSongList() {
        songList.clear();
    }
    public fun getSongList(): MutableList<Track> {
        return songList;
    }


    fun clearDate() {
        this.clearSongList();
        this.setId(0);
    }


    fun setSP(a: SharedPreferences) {
        this.preferences = a;
    }

}