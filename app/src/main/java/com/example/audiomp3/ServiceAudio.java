package com.example.audiomp3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServiceAudio extends Service {

MediaPlayer mediaPlayer;

@Override
    public void onCreate(){




    }

    @Override

    public int onStartCommand(Intent intent, int flag, int idProcess){


        mediaPlayer= MediaPlayer.create(this,R.raw.electro);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        return START_STICKY;
    }

    @Override
    public void onDestroy(){


     mediaPlayer.stop();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
