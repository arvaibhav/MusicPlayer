/**
 * Created by xuanyichen on 1/1/17.
 */

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest {

    // Constructor
    public SoundClipTest() {
    }

    public static void main(String[] args) {

        MainForm mainForm = new MainForm();
        /*try {
            // Open an audio input stream.
            File soundFile = new File("RX-0.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
// from a URL
            //URL url = new URL("http://www.zzz.com/eatfood.wav");
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }*/
    }
}