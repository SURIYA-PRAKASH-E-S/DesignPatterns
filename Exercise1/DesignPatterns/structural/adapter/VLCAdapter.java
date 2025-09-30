// Adapter: Makes VLC compatible with MediaPlayer
public class VLCAdapter implements MediaPlayer {

    private AdvancedMediaPlayer vlcPlayer;

    public VLCAdapter() {
        vlcPlayer = new VLCPlayer();
    }

    @Override
    public void play(String fileName) {
        vlcPlayer.playVLC(fileName);
    }
}
