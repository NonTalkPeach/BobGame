package game;

import java.io.*;
import javax.sound.sampled.*;

public class Music {    //ʵ�ֲ�������
    File Die1;
    Clip clip;

    public Music(String s) {
        Die1 = new File(s);
    }

    public void play() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Die1));
            clip.start();                //��������Ч������
        } catch (Exception ignored) {

        }

    }

}
