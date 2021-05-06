package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

    }

    public static class ScreenReceiver extends BroadcastReceiver {

        public static boolean pantalla = true;

        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                Log.e("estado", "Pantalla Apagada");
                t = new Timer();

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        MediaPlayer mp = MediaPlayer.create(context, R.raw.claxon);
                        mp.start();
                    }
                };
                t.schedule(timerTask, 10000,10000);
                pantalla = false;
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.e("estado", "Pantalla Encendida");
                t.cancel();
                Toast.makeText(context, "La Pantalla está Encendida", Toast.LENGTH_LONG).show();
                pantalla = true;
            }
        }

    }


}