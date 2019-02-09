package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class RetoView extends AppCompatActivity {

    public static int idReto;
    ArrayList<Reto> reto;
    MediaPlayer mpvoice = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        reto = new ArrayList<Reto>();

        reto.add(new Reto(R.drawable.reto_cama_correcto,R.drawable.reto_cama_incorrecto, "dormir.wav"));
        reto.add(new Reto(R.drawable.reto_baul_correcto,R.drawable.reto_baul_incorrecto, "lampara.wav"));
        reto.add(new Reto(R.drawable.reto_ventana_correcto, R.drawable.reto_ventana_incorrecto, "ventilador.wav"));
        reto.add(new Reto(R.drawable.reto_closet_correcto, R.drawable.reto_closet_incorrecto, "televisor.wav"));

        ImageView imagen_correcta=(ImageView) findViewById(R.id.imagen_correcta);
        ImageView imagen_incorrecta=(ImageView) findViewById(R.id.imagen_incorrecta);

        ImageView home_reto = (ImageView) findViewById(R.id.home_reto);
        home_reto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Room_view.class));
                finish();
            }
        });

        GenerarReto(imagen_correcta, imagen_incorrecta);

        try
        {
            AssetFileDescriptor afd = getAssets().openFd(reto.get(idReto).getVoz());
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

    }

    public void GenerarReto(ImageView correcta,ImageView incorrecta){

        int numeroAleatorio = (int) (Math.random()*2+1);


        // ganador es la imageview ganadaor
        if(numeroAleatorio==1){
            correcta.setBackgroundResource(reto.get(idReto).getGanador());
            incorrecta.setBackgroundResource(reto.get(idReto).getPerdedor());

            correcta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultReto.resultado=true;
                    startActivity(new Intent(getApplicationContext(),ResultReto.class));
                    finish();
                }
            });
            incorrecta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultReto.resultado=false;
                    startActivity(new Intent(getApplicationContext(),ResultReto.class));
                    finish();
                }
            });

        }else{
            incorrecta.setBackgroundResource(reto.get(idReto).getGanador());
            correcta.setBackgroundResource(reto.get(idReto).getPerdedor());

            incorrecta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultReto.resultado=true;
                    startActivity(new Intent(getApplicationContext(),ResultReto.class));
                    finish();
                }
            });
            correcta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultReto.resultado=false;
                    startActivity(new Intent(getApplicationContext(),ResultReto.class));
                    finish();
                }
            });

        }

    }


    protected void onPause() {
        super.onPause();
        if(MainActivity.mp.isPlaying())
        {
            MainActivity.mp.pause();

        }
        if(mpvoice.isPlaying())
        {
            mpvoice.pause();
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

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Room_view.class));
        finish();
    }


}
