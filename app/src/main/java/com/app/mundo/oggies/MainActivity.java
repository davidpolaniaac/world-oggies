package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mp = new MediaPlayer();
    private ImageButton botonAudio;
    public static boolean muted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        /*Reset de jugador*/
        Jugador.creado=false;
        Jugador.marcosDesbloqueados=2;
        Jugador.gender=0;
        Jugador.head=0;
        Jugador.headRecurso=0;
        Jugador.body=0;
        Jugador.accessory=0;
        Jugador.estacion=0;
        Jugador.window=0;
        Jugador.closet=0;
        Jugador.trunk=0;
        Jugador.bed=0;
        Jugador.gender=0;

        Typeface dimbo = Typeface.createFromAsset(getAssets(),  "fonts/dimbo.ttf");



        Button Ingresar= (Button) findViewById(R.id.id_boton_ingresar);
        Ingresar.setAllCaps(false);
        Ingresar.setTypeface(dimbo);
        Ingresar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(muted==false)
                {
                    mp.setVolume(Float.parseFloat("0.5"), Float.parseFloat("0.5"));
                }
                startActivity(new Intent(getApplicationContext(), AnimacionInicial.class));

            }
        });
        Button Padres= (Button) findViewById(R.id.id_boton_padres);
        Padres.setAllCaps(false);
        Padres.setTypeface(dimbo);
        Padres.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Main_parents.class));

                if(muted==false)
                {
                    mp.setVolume(Float.parseFloat("0.5"), Float.parseFloat("0.5"));
                }
                startActivity(new Intent(getApplicationContext(), Main_parents.class));
                //startActivity(new Intent(getApplicationContext(), MarcosFotos.class));

            }
        });

        try {
            AssetFileDescriptor afd = getAssets().openFd("mundooggies.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.setLooping(true);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        botonAudio = (ImageButton)findViewById(R.id.boton_audio);
        botonAudio.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(muted==true)
                {
                    muted=false;
                    mp.setVolume(Float.parseFloat("1.0"),Float.parseFloat("1.0"));
                    botonAudio.setBackgroundResource(R.drawable.boton_music_on);
                }
                else {
                    muted=true;
                    mp.setVolume(Float.parseFloat("0.0"),Float.parseFloat("0.0"));
                    botonAudio.setBackgroundResource(R.drawable.boton_music_off);
                }


            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this.mp.isPlaying())
        {
            mp.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!this.mp.isPlaying())
        {
            mp.start();
        }

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
