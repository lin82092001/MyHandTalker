package com.example.smartglovev2;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by 林北94狂 on 2017/11/17.
 */

public class VoiceData
{
    public int hello;
    public int i;
    public int you;
    public int thanks;
    public int welcome;
    public int come;
    public int love;
    public int lonely;
    public int help;
    public int admin;
    public int protect;
    public int taipei;
    public int taiwan;
    public int technology;
    public int university;
    public int need;
    public int coffee;
    public int sandwich;
    public int study;
    public int letter;
    public int recletter;
    public int stamp;
    public int pay;
    public int howmuch;
    public int price;

    public VoiceData()
    {

    }

    public void say(final Context context, final int id){
        new Runnable() {
            @Override
            public void run() {
                try {
                    //https://stackoverflow.com/questions/12154951/android-mediaplayer-create
                    final MediaPlayer player = MediaPlayer.create(context, id);
                    if (player != null) {
                        player.stop();
                    }
                    if (!player.isPlaying())
                    {player.prepare();
                        player.start();
                    }
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            player.release();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }
}
