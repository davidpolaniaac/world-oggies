package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class RoomEdit extends AppCompatActivity {

    ViewFlipper mViewFlipper;
    static Room bedroom;
    ArrayList<Integer> elemetos;
    public float init_x;
    public int idElemento=0; //0 = cama, 1= baul, 2= ventana, 3 = closet
    HashTableCloset hashCloset= new HashTableCloset();
    private MediaPlayer mpvoice1 = new MediaPlayer();
    private MediaPlayer mpvoice2 = new MediaPlayer();
    ImageButton cama;
    ImageButton baul;
    ImageButton ventana;
    ImageButton closet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.room_edit);

        elemetos=bedroom.getBed();

        ImageView accepted=(ImageView)findViewById(R.id.id_boton_accept_item_room);
        cama = (ImageButton) findViewById(R.id.id_boton_cama1);
        baul = (ImageButton) findViewById(R.id.id_boton_baul1);
        ventana = (ImageButton) findViewById(R.id.id_boton_ventana1);
        closet = (ImageButton) findViewById(R.id.id_boton_closet1);


        ImageButton cancelar = (ImageButton) findViewById(R.id.id_boton_decline_room_edit);

        ImageButton buttonNext = (ImageButton ) findViewById(R.id.id_next_item_room);
        ImageButton buttonPrevious = (ImageButton ) findViewById(R.id.id_previous_item_room);

        mViewFlipper = (ViewFlipper) findViewById(R.id.id_item_room);

        onclickButtonElementos(cama, baul, ventana, closet);

        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        mViewFlipper.setOnTouchListener(new ListenerTouchViewFlipper());

        onclickButtonEvent(buttonNext, buttonPrevious);

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("editar_habitacion1.wav");
            mpvoice1.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mpvoice1.prepare();
            mpvoice1.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        mpvoice1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try
                {

                    AssetFileDescriptor afd = getAssets().openFd("editar_habitacion2.wav");
                    mpvoice2.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mpvoice2.prepare();
                    mpvoice2.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mpvoice2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MainActivity.mp.start();
            }
        });

        CargarComponetes();

        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Room_view.class));
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Room_view.class));
                finish();
            }
        });
        
    }

    private void CargarComponetes(){

        mViewFlipper.removeAllViews();

        for (int i = 0; i < elemetos.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(elemetos.get(i));
            mViewFlipper.addView(imageView);
        }

    }
    private void onclickButtonElementos(ImageView cama,ImageView baul,ImageView ventana,ImageView closet){

        cama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                elemetos=bedroom.getBed();
                idElemento=0;
                calcularBotones();
                CargarComponetes();
            }
        });
        baul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                elemetos=bedroom.getTrunk();
                idElemento=1;
                calcularBotones();
                CargarComponetes();
            }
        });
        ventana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                elemetos= bedroom.getWindow();
                idElemento=2;
                calcularBotones();
                CargarComponetes();
            }
        });
        closet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                elemetos=bedroom.getCloser();
                idElemento=3;
                calcularBotones();
                CargarComponetes();
            }
        });
    }
    private void onclickButtonEvent(ImageButton imageButtonNext,ImageButton imageButtonPrevious){
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                mViewFlipper.showNext();
            }
        });
        imageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));
                mViewFlipper.showPrevious();
            }
        });
    }
    private class ListenerTouchViewFlipper implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
                    init_x=event.getX();
                    return true;
                case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
                    float distance = init_x - event.getX();

                    if(distance>0)
                    {
                        mViewFlipper.showPrevious();
                        mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));

                    }else if(distance<0)
                    {
                        mViewFlipper.showNext();
                        mViewFlipper.setBackgroundColor(getResources().getColor(R.color.transparente));

                    }else{

                        mViewFlipper.setBackgroundColor(getResources().getColor(R.color.colorSeleccionado));
                        int recurso=elemetos.get(mViewFlipper.getDisplayedChild());

                        if(idElemento==0){

                            Jugador.bed=recurso;

                        }else if(idElemento==1){

                            Jugador.trunk=recurso;

                        }else if(idElemento==2){

                            Jugador.window=recurso;

                        }else{

                            int elemento = hashCloset.hashtableCloset.get(recurso);
                            Jugador.closet=elemento;
                        }

                    }


                default:
                    break;
            }
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(MainActivity.mp.isPlaying())
        {
            MainActivity.mp.pause();
        }
        if(mpvoice1.isPlaying())
        {
            mpvoice1.pause();
        }
        if(mpvoice2.isPlaying())
        {
            mpvoice2.pause();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!MainActivity.mp.isPlaying() && !mpvoice1.isPlaying() && !mpvoice2.isPlaying())
        {
            MainActivity.mp.start();
        }

    }

    protected void calcularBotones()
    {
        //verano seleccionado
        if(idElemento==0)
        {
            cama.setBackgroundResource(R.drawable.boton_cama2);
            baul.setBackgroundResource(R.drawable.boton_baul1);
            ventana.setBackgroundResource(R.drawable.boton_ventana1);
            closet.setBackgroundResource(R.drawable.boton_armario1);
        }
        //Otono seleccionado
        else if(idElemento==1)
        {
            cama.setBackgroundResource(R.drawable.boton_cama1);
            baul.setBackgroundResource(R.drawable.boton_baul2);
            ventana.setBackgroundResource(R.drawable.boton_ventana1);
            closet.setBackgroundResource(R.drawable.boton_armario1);
        }
        //Invierno seleccionado
        else if(idElemento==2)
        {
            cama.setBackgroundResource(R.drawable.boton_cama1);
            baul.setBackgroundResource(R.drawable.boton_baul1);
            ventana.setBackgroundResource(R.drawable.boton_ventana2);
            closet.setBackgroundResource(R.drawable.boton_armario1);
        }
        //Primavera seleccionada
        else if(idElemento==3)
        {
            cama.setBackgroundResource(R.drawable.boton_cama1);
            baul.setBackgroundResource(R.drawable.boton_baul1);
            ventana.setBackgroundResource(R.drawable.boton_ventana1);
            closet.setBackgroundResource(R.drawable.boton_armario2);
        }
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Room_view.class));
        finish();
    }

}
