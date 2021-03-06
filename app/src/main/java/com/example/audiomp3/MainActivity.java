package com.example.audiomp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void reproducir(View v) {

      //Descomentar esta linea para iniciar un Background Service(Disponible solo en versiones inferiores a oreo)
      //startService(new Intent(this, ServiceAudio.class));

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, ForegroundAudio.class));
        }

    }

    //CARGAR SONIDOS ADICIONALES
    public void sax(View v){

        mediaPlayer=MediaPlayer.create(this,R.raw.sax);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void voice1(View v){

        mediaPlayer=MediaPlayer.create(this,R.raw.voice1);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void voice2(View v){

        mediaPlayer=MediaPlayer.create(this,R.raw.voice2);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void voice3(View v){

        mediaPlayer=MediaPlayer.create(this,R.raw.voice3);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        //mediaPlayer.stop();
    }


    public void parar(View v){


        stopService(new Intent(getBaseContext(),ServiceAudio.class));
        stopService(new Intent(getBaseContext(), ForegroundAudio.class));

    }

}