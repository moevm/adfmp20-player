package audio

import android.media.MediaPlayer

class Audio {

    private val mediaPlayer = MediaPlayer();

    public fun set(url: String) {
        this.mediaPlayer.setDataSource(url);
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



}