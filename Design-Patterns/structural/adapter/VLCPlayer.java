// Concrete Adaptee
public class VLCPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}
