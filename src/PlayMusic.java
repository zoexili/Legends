import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This file plays background gaming music for Legend of Valor.
 */
public class PlayMusic {
    public static void playMusic(String filepath) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(100);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
