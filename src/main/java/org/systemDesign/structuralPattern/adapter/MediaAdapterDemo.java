package org.systemDesign.structuralPattern.adapter;

public class MediaAdapterDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("vlc", "movie.vlc");
        player.play("avi", "film.avi");
    }
}
// Adaptee (incompatible interface - advanced player)
interface AdvancedMediaPlayer{
    void playVLC(String fileName);
    void playMP4(String fileName);
}
// Concrete Adaptee classes
class VLCPlayer implements AdvancedMediaPlayer{

    @Override
    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
    @Override
    public void playMP4(String fileName) {}
}
// Concrete Adaptee classes
class MP4Player implements AdvancedMediaPlayer{

    @Override
    public void playVLC(String fileName) {}
    @Override
    public void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}
// Target interface (what client expects)
interface Media{
    void play(String audioType, String fileName);
}
// Adapter (makes AdvancedMediaPlayer compatible with MediaPlayer)
class MediaAdapter implements Media{
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("VLC")){
            advancedMediaPlayer = new VLCPlayer();
        }
        else{
            advancedMediaPlayer = new MP4Player();
        }
    }
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("VLC")){
            advancedMediaPlayer.playVLC(fileName);
        }
        else{
            advancedMediaPlayer.playMP4(fileName);
        }
    }
}
// Concrete Target (can play MP3 natively)
class AudioPlayer implements Media{
    MediaAdapter mediaAdapter;
    @Override
    public void play(String audioType, String fileName) {
        // Built-in support for MP3
        if(audioType.equalsIgnoreCase("MP3")){
            System.out.println("Playing MP3 file: " + fileName);
        }
        // Use adapter for other formats
        else if (audioType.equalsIgnoreCase("VLC") ||
                audioType.equalsIgnoreCase("MP4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid format: " + audioType);
        }
    }
}