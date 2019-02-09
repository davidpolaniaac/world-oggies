package com.app.mundo.oggies;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Bienvenido extends AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 3000; // 3 segundos
    MediaPlayer mpvoice = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.welcome);

        LinearLayout linearLayoutEstacion = (LinearLayout) findViewById(R.id.estacio);
        LinearLayout linearLayoutBase=(LinearLayout)findViewById(R.id.Base);

        ImageView head=(ImageView) findViewById(R.id.head_welcome);
        ImageView body=(ImageView) findViewById(R.id.body_welcome);
        ImageView accesory=(ImageView) findViewById(R.id.accesorie_welcome);


        linearLayoutEstacion.setBackgroundResource(Jugador.estacion);
        linearLayoutBase.setBackgroundResource(Jugador.head);

        if(Jugador.body!=0){
            body.setBackgroundResource(Jugador.body);
        }
        if(Jugador.accessory!=0){
            accesory.setBackgroundResource(Jugador.accessory);
        }

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("bienvenido.wav");
            mpvoice.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mpvoice.prepare();
            mpvoice.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mpvoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MainActivity.mp.start();
            }
        });



        new Handler().postDelayed(new Runnable() {
            public void run() {
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(Bienvenido.this, Room_view.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(MainActivity.mp.isPlaying())
        {
            MainActivity.mp.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!MainActivity.mp.isPlaying() && !mpvoice.isPlaying())
        {
            MainActivity.mp.start();
        }

    }
}
