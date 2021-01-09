package com.example.android_f_work;

//import java.security.Provider;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class musicService extends Service {

    public musicService() {

    }
    public static MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("music", "onCreate: musicService start");
        mediaPlayer = MediaPlayer.create(this,R.raw.wonderland);
        this.mediaPlayer.start();
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                Log.e("smc",""+i);
            }
        });

    }

    @Override
    public void onDestroy() {
        Log.d("music", "onCreate: musicService destory");
        super.onDestroy();
        this.mediaPlayer.stop();
    }
    public static void pause(){
        mediaPlayer.pause();
    }
/*    public static int get_time(){
        return mediaPlayer.getDuration();
    }
    public static int get_now_time(){
        return mediaPlayer.getCurrentPosition();
    }
    public static void seek(int pos){
//        Log.e("smc",pos/100.0*get_time()+"");
        mediaPlayer.seekTo((int)(pos/100.0*get_time()));
    }*/
    public static void mediaStart(){
        mediaPlayer.start();
    }
}
