// Client code
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Adapter Pattern ---\n");
        MediaPlayer mp3 = new MP3Player();
        MediaPlayer mp4 = new MP4Player();
        MediaPlayer vlc = new VLCAdapter();

        mp3.play("song.mp3");
        mp4.play("movie.mp4");
        vlc.play("video.vlc");
    }
}
