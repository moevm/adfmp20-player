package audio


import android.media.MediaPlayer
import android.util.Log
import bus.Bus
import java.util.*


/**
 * changeActivePlayer
 * changeTrackPlayer
 * changeFilterPlayer
 *
 * */

object Audio {


    private val mediaPlayer = MediaPlayer();
    private var position: Int = 0;
    private var listSong: List<Track> = listOf();
    private var filter: String? = null;


    private var isActive = false;


    init {
        mediaPlayer.setOnSeekCompleteListener {
            Log.v("SeekTime", "${mediaPlayer.currentPosition}");
        }

        mediaPlayer.setOnPreparedListener {
            if (isActive) {
                mediaPlayer.start()
            }
            Bus.emit("changeActivePlayer");
            Bus.emit("changeTrackPlayer");
        }

        mediaPlayer.setOnCompletionListener{

            if (currentDuration() != 0 ) {
                next();
                play();
            }



        }
    }

    fun onPrepared(player: MediaPlayer) {
        player.start()
    }
    public fun set(url: String) {

        isActive = this.mediaPlayer.isPlaying;

        if (isActive)
            this.mediaPlayer.pause();
        mediaPlayer.reset();

        this.mediaPlayer.setDataSource(url);
        this.mediaPlayer.prepareAsync();




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
        isActive = true;
        Bus.emit("changeActivePlayer");
    }
    public fun pause() {
        this.mediaPlayer.pause();
        isActive = false;
        Bus.emit("changeActivePlayer");
    }
    public fun loop(value: Boolean = !mediaPlayer.isLooping) {
        mediaPlayer.isLooping = value;
    }

    public fun currentElem(): Track{

        if (listSong.isEmpty()) {


            return Track();
        } else
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
        println("+@")
        listSong = songs;

        setTrackByIndex(0);
        println("+1")
    }
    public fun getList(): List<Track> {
        if (filter == null)
            return listSong;
        else {

            return listSong.filter {  it.title.toLowerCase(Locale.ROOT).indexOf(filter!!) != -1 || it.artist.toLowerCase(Locale.ROOT).indexOf(filter!!) != -1 }
        }
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

    fun setFilter(str: String) {
        filter = if (str.isEmpty()) {
            null;
        } else {
            str.toLowerCase(Locale.ROOT);
        }

        Bus.emit("changeFilterPlayer")
    }

}
