package com.romsteam.clicker.engine.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {

    private static final String EXPLOSION_TRANSITION_SOUND_PATH = "/sounds/explosionTransition.wav";
    public static SoundClip EXPLOSION_TRANSITION_SOUND = new SoundClip(EXPLOSION_TRANSITION_SOUND_PATH);

    private Clip clip;
    private FloatControl gainGontrol;

    public SoundClip(String path){
        try {
            InputStream audioSource = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedInput = new BufferedInputStream(audioSource);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInput);
            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                        baseFormat.getSampleRate(),
                                            16,
                                                        baseFormat.getChannels(),
                                                baseFormat.getChannels()*2,
                                                        baseFormat.getSampleRate(),
                                                false);
            AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat,audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(decodedAudioInputStream);

            gainGontrol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        if(clip==null){
            return;
        }
        clip.setFramePosition(0);
        while(!clip.isRunning())
            clip.start();

    }

    public void stop(){
        if(clip.isRunning())
            clip.stop();
    }

    public void close(){
        stop();
        clip.drain();
        clip.close();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void setVolume(float value){
        gainGontrol.setValue(value);
    }

    public boolean isRunning(){
        return clip.isRunning();
    }
}
