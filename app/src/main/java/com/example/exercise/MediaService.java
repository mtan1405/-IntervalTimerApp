package com.example.exercise;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaService extends Service {
    public static final String START = "com.exercise.start";
    public MediaService() {
    }
    BroadcastReceiver broadcastReceiver ;
    MediaPlayer mediaPlayer ;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer == null ){
            mediaPlayer = MediaPlayer.create(Application.getInstance() , R.raw.mus) ;
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mediaPlayer.start();
                }
            };
        }
        IntentFilter intentFilter =new IntentFilter() ;
        intentFilter.addAction(START) ;
        registerReceiver(broadcastReceiver , intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        if (mediaPlayer != null)
        mediaPlayer.release();
        super.onDestroy();
    }


}