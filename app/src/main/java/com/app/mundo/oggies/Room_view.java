package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Room_view extends AppCompatActivity {

    ImageView windowRoom;
    ImageView closetRoom;
    ImageView baulRoom;
    ImageView bedRoom;

    ImageButton editarPersonaje;
    ImageButton muteRoom;
    ImageButton salir;
    ImageButton editarCuarto;
    ImageButton botonExplorar;
    ImageButton botonCamara;

    ImageView body;
    ImageView accessory;

    LinearLayout Head;

    private MediaPlayer mpExplorar = new MediaPlayer();
    private MediaPlayer mpEditar = new MediaPlayer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.room_view);

        Jugador.creado=true;

        editarPersonaje = (ImageButton) findViewById(R.id.id_boton_editar_personaje);
        salir = (ImageButton) findViewById(R.id.exit);
        muteRoom = (ImageButton) findViewById(R.id.room_mute);
        if(MainActivity.muted==true)
        {
            muteRoom.setBackgroundResource(R.drawable.boton_music_off);
        }
        else {
            muteRoom.setBackgroundResource(R.drawable.boton_music_on);
        }
        editarCuarto = (ImageButton) findViewById(R.id.id_boton_editar_cuarto);
        windowRoom = (ImageView) findViewById(R.id.id_image_window_room);
        closetRoom = (ImageView) findViewById(R.id.id_image_closet_room);
        baulRoom =(ImageView) findViewById(R.id.id_image_baul_room);
        bedRoom =(ImageView) findViewById(R.id.id_image_bed_room);
        botonCamara=(ImageButton) findViewById(R.id.id_boton_camara);
        botonExplorar=(ImageButton) findViewById(R.id.id_boton_explorar);

        body=(ImageView) findViewById(R.id.body_select_habitacion);
        accessory=(ImageView) findViewById(R.id.accesorio_select_room);

        Head=(LinearLayout) findViewById(R.id.cuerpo_habitacion);
        Head.setBackgroundResource(Jugador.head);



        editarPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CharacterEditor.class));
                finish();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        muteRoom.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.muted==true)
                {
                    MainActivity.muted=false;
                    MainActivity.mp.setVolume(Float.parseFloat("0.5"), Float.parseFloat("0.5"));
                    muteRoom.setBackgroundResource(R.drawable.boton_music_on);
                }
                else {
                    MainActivity.muted=true;
                    MainActivity.mp.setVolume(Float.parseFloat("0.0"), Float.parseFloat("0.0"));
                    muteRoom.setBackgroundResource(R.drawable.boton_music_off);
                }


            }
        });

        editarCuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RoomEdit.class));
                finish();
            }
        });

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("editar_habitacion.wav");
            mpEditar.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mpEditar.prepare();
            mpEditar.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        mpEditar.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if (Jugador.bed != 0 && Jugador.window != 0 && Jugador.closet != 0 && Jugador.trunk != 0) {
                    try {
                        AssetFileDescriptor afd = getAssets().openFd("explorar.wav");
                        mpExplorar.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        mpExplorar.prepare();
                        mpExplorar.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    if (!MainActivity.mp.isPlaying()) {
                        MainActivity.mp.start();
                    }
                }

            }
        });

        mpExplorar.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (!MainActivity.mp.isPlaying()) {
                    MainActivity.mp.start();
                }
            }
        });

        CargarElementos();
    }

    public void CargarElementos(){

        if(Jugador.body!=0){
            body.setBackgroundResource(Jugador.body);
        }
        if(Jugador.accessory!=0){
            accessory.setBackgroundResource(Jugador.accessory);
        }

        if(Jugador.bed!=0){
            bedRoom.setBackgroundResource(Jugador.bed);
        }
        if(Jugador.window!=0){
            windowRoom.setBackgroundResource(Jugador.window);
        }
        if(Jugador.closet!=0){
            closetRoom.setBackgroundResource(Jugador.closet);
        }
        if(Jugador.trunk!=0){
            baulRoom.setBackgroundResource(Jugador.trunk);
        }
        if (Jugador.bed!=0 && Jugador.window!=0 && Jugador.closet!=0 && Jugador.trunk!=0){

            botonCamara.setVisibility(View.VISIBLE);
            botonExplorar.setVisibility(View.VISIBLE);

            botonCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),MarcosFotos.class));
                    finish();
                }
            });

            botonExplorar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ExplorarHabitacion.class));
                    finish();
                }
            });
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(MainActivity.mp.isPlaying())
        {
            MainActivity.mp.pause();
        }
        if(mpEditar.isPlaying())
        {
            mpEditar.pause();
        }
        if(mpExplorar.isPlaying())
        {
            mpExplorar.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!MainActivity.mp.isPlaying() && !mpEditar.isPlaying() && !mpExplorar.isPlaying())
        {
            MainActivity.mp.start();
        }

    }

}
