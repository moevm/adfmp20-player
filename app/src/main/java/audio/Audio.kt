package audio


import android.media.MediaActionSound
import android.media.MediaPlayer
import android.util.Log
import bus.Bus
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.concurrent.schedule


/**
 * changeActivePlayer
 * changeTrackPlayer
 *
 * */

object Audio {


    private val mediaPlayer = MediaPlayer();
    private var position: Int = 0;
    private lateinit var listSong: List<Track>;


    init {
        mediaPlayer.setOnSeekCompleteListener {
            Log.v("SeekTime", "${mediaPlayer.currentPosition}");
        }
    }

    public fun set(url: String) {
        val trackActive = this.mediaPlayer.isPlaying;

        this.pause();
        mediaPlayer.reset();


        this.mediaPlayer.setDataSource(url);
        this.mediaPlayer.prepareAsync();

        Bus.emit("changeTrackPlayer");

        Log.v("Play", "0")
        if (trackActive) {
            Log.v("Play", "-")
            Timer().schedule(1000) {
                Log.v("Play", "+")
                play();
            }

        }

    }
    public fun set(elem: Track) {


        this.set(elem.urlTrack);



    }
    public fun toggle() {
        if (this.mediaPlayer.isPlaying){
            this.pause();
        } else {
            this.play();
        }
    }
    public fun play() { 
        this.mediaPlayer.start();
        Bus.emit("changeActivePlayer");
    }
    public fun pause() {
        this.mediaPlayer.pause();
        Bus.emit("changeActivePlayer");
    }
    public fun loop(value: Boolean = !mediaPlayer.isLooping) {
        mediaPlayer.isLooping = value;
    }

    public fun currentElem(): Track{

        return listSong[position];
    }
    public fun currentDuration(): Int {
        return mediaPlayer.currentPosition / 1000;
    }
    public fun durationTrack(): Int {
        return mediaPlayer.duration / 1000;
    }

    public fun setTrackByIndex(index: Int) {

        position = index;

        update();
    }

    public fun setTrackByUrl(url: String) {

        for((index, value) in listSong.withIndex()) {

            if (value.urlTrack == url) {

                position = index;
                break;
            }

        }

        update();

    }


    private fun update(){



        this.set(this.currentElem());
    }

    public fun next(){
        checkPosition(1);

        update();

    }
    public fun prev(){
        checkPosition(-1);
        update();
    }
    public fun setList(songs: List<Track>){
        listSong = songs;
        setTrackByIndex(1);
    }
    public fun getList(): List<Track> {
        return listSong;
    }

    public fun isActive(): Boolean {
        return mediaPlayer.isPlaying;
    }
    public fun setCurrentTimeByProgress(progress: Int){

        var durationTrack   = (mediaPlayer.duration / 1000).toInt();

        var newCurrentTime:Int = (durationTrack * (progress.toFloat() / 100)).toInt() * 1000;


        mediaPlayer.seekTo(newCurrentTime)
    }

    private fun checkPosition(a: Int){
        this.position += a;

        if (position >= listSong.size) position = 0;
        if (position < 0) position = (listSong.size - 1);

    }

}