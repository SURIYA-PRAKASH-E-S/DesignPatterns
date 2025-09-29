// Concrete Media Player for MP3
public class MP3Player implements MediaPlayer {

    @Override
    public void play(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}
