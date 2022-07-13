package com.example.audiomp3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundAudio extends Service {

    private static final String CHANNEL_ID = "My service";
    MediaPlayer mediaPlayer;

    @Override

    public int onStartCommand(Intent intent, int flag, int idProcess) {

        //Carga un sonido de la carpeta raw y comienza a reproducirlo en loop
        mediaPlayer = MediaPlayer.create(this, R.raw.electro);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        //Manda a llamar la actividad principal una vez que el usuario haga clic en la notificacion
        Intent intente = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intente, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //Esta lineas de codigo solo correra en dispositivos de oreo para arriba
            CharSequence name = "Service";

            //Crear el Canal de noitificaciones
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

        //Crear la notificaion
        Notification notification =
                new NotificationCompat.Builder(this, CHANNEL_ID)

                        .setContentTitle("Titulo de la notificacion") //Titulo de la notificacion
                        .setContentText("Descripcion de la notificacion")
                        .setSmallIcon(R.drawable.ic_launcher_background) //Icono de la notificacion

                        //If you were to turn on accessibility services in your device
                        // (for like visually challenged people), the text passed onto
                        // setTicker() will be audibly announced.

                        .setTicker("TickerText")

                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent)
                        .build();


        startForeground(1001, notification); //Iniciar el servicio en Foreground
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
