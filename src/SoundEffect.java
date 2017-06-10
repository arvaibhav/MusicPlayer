import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Created by xuanyichen on 5/4/17.
 */
public enum SoundEffect {
    RX_0("RX-0.wav"), // RX-0
    SONG_OF_WIND("Kaze_no_Uta.wav"); // gong


    // Nested class for specifying defVolume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH, MAX
    }

    public static Volume defVolume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    public boolean loop = false;
    private boolean loopStarted;

    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (defVolume != Volume.MUTE) {
            if (clip.isRunning()) {
                clip.stop();   // Stop the player if it is still running
            }
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    public void loop() {
        new Thread(new Runnable() {
            public void run() {
                while (loop) {
                    if (defVolume != Volume.MUTE && !clip.isRunning()) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY); // Start playing
                    }

                    try {
                        Thread.sleep(clip.getMicrosecondLength() / clip.getFrameLength());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void startLoop() {
        if (loopStarted != true) {
            loop = true;
            loop();
        }
    }

    public void stopLoop() {
        loop = false;
        clip.loop(0);
    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void pause() {
        clip.stop();
        clip.setFramePosition(clip.getFramePosition());
    }

    public void resume() {
        clip.start();
    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    }
}
