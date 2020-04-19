package audio

import android.media.MediaPlayer
import android.util.Log

object Audio {

    private val mediaPlayer = MediaPlayer();
    private var position: Int = 0;
    private lateinit var listSong: List<Track>;


    public fun set(url: String) {
        this.mediaPlayer.setDataSource(url);
        this.mediaPlayer.prepare();
    }
    public fun set(elem: Track) {
        this.mediaPlayer.setDataSource(elem.urlTrack);
        this.mediaPlayer.prepare();
    }
    public fun play() { 
        this.mediaPlayer.start();
    }
    public fun pause() {
        this.mediaPlayer.pause();
    }
    public fun loop(value: Boolean = !mediaPlayer.isLooping) {
        mediaPlayer.isLooping = value;
    }

    public fun currentElem(): Track{

        return listSong[position];
    }

    public fun setTrackByIndex(index: Int) {

        position = index;

        set(currentElem())


    }

    public fun next(){}
    public fun prev(){}
    public fun setList(songs: List<Track>){
        listSong = songs;
        setTrackByIndex(0);
    }

}