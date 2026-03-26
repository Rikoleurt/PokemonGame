package Utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class SongManager {

    private static SongManager instance;
    private MediaPlayer mediaPlayer;
    private String currentSong;
    private double volume = 0.0; // Mute sound by default

    private SongManager() {}

    public static SongManager getInstance() {
        if (instance == null) {
            instance = new SongManager();
        }
        return instance;
    }

    public void playSong() {
        playSong("/music/champion_steven.mp3", true);
    }

    public void playSong(String path) {
        playSong(path, true);
    }

    public void playSong(String path, boolean loop) {
        if (path == null) {
            return;
        }

        if (mediaPlayer != null && path.equals(currentSong)) {
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
            return;
        }

        stopSong();

        String songPath = Objects.requireNonNull(SongManager.class.getResource(path)).toExternalForm();
        Media media = new Media(songPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if (loop) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }

        mediaPlayer.play();
        currentSong = path;
    }

    public void stopSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        currentSong = null;
    }

    public void pauseSong() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resumeSong() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void setVolume(double volume) {
        this.volume = Math.max(0.0, Math.min(1.0, volume));
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(this.volume);
        }
    }

    public double getVolume() {
        return volume;
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public String getCurrentSong() {
        return currentSong;
    }
}