import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by xuanyichen on 5/4/17.
 */
public class MainForm extends JFrame {

    private JButton playButton_1;
    private JPanel mainPanel;
    private JButton playButton_2;
    private JButton stopButton_1;
    private JSlider volumeSlider;
    private JLabel volumeLabel;

    public MainForm() {

        SoundEffect.init();
        SoundEffect.defVolume = SoundEffect.Volume.LOW;
        volumeSlider.setInverted(false);
        playButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playButton_1.getText().equals("Play RX-0")) {
                    SoundEffect.RX_0.play();
                    playButton_1.setText("Pause");
                }
                if(playButton_1.getText().equals("Pause")){
                    SoundEffect.RX_0.pause();
                    playButton_1.setText("Resume RX-0");
                }
                else {
                    SoundEffect.RX_0.resume();
                    playButton_1.setText("Pause");
                }
            }
        });
        playButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playButton_2.getText().equals("Play 風ノ歌")) { // play click
                    SoundEffect.SONG_OF_WIND.play();
                    playButton_2.setText("Pause");
                }
                else if(playButton_2.getText().equals("Pause")){ // pause click
                    SoundEffect.SONG_OF_WIND.pause();
                    playButton_2.setText("Resume 風ノ歌");
                }
                else { // resume click
                    SoundEffect.SONG_OF_WIND.resume();
                    playButton_2.setText("Pause");
                }

            }
        });
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int volVal = volumeSlider.getValue();
                volumeLabel.setText(volVal + "%");
                if (volVal == 0) SoundEffect.defVolume = SoundEffect.Volume.MUTE;
                else if (volVal <= 25) SoundEffect.defVolume = SoundEffect.Volume.LOW;
                else if (volVal <= 50) SoundEffect.defVolume = SoundEffect.Volume.MEDIUM;
                else if (volVal <= 75) SoundEffect.defVolume = SoundEffect.Volume.HIGH;
                else SoundEffect.defVolume = SoundEffect.Volume.MAX;
            }
        });
        stopButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (SoundEffect song: SoundEffect.values()) {
                    song.stop();
                    playButton_1.setText("Play RX-0");
                    playButton_2.setText("Play 風ノ歌");
                }
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Hello World");
        this.setSize(600, 500);
        setContentPane(mainPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {

    }
}
