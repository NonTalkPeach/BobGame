package game;

import java.io.*;
import javax.sound.sampled.*;

public class Music {    //实现播放音乐
    File Die1;
    Clip clip;

    public Music(String s) {
        Die1 = new File(s);
    }

    public void play() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Die1));
            clip.start();                //播放死亡效果音乐
        } catch (Exception ignored) {

        }

    }

}
