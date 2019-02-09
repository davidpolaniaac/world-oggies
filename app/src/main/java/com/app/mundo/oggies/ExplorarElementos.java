package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

/**
 * Created by PC on 16/05/2016.
 */
public class ExplorarElementos extends Fragment {

    ViewFlipper mViewFlipper;
    ImageButton boton_anterior_marcos;
    ImageButton boton_siguiente_marcos;
    public float init_x;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.explorar_objetos_habitacion, container, false);

        mViewFlipper=(ViewFlipper) rootView.findViewById(R.id.viewFlipper);
        boton_siguiente_marcos=(ImageButton)rootView.findViewById(R.id.boton_siguiente_marcos);
        boton_anterior_marcos=(ImageButton)rootView.findViewById(R.id.boton_anterior_marcos);

        onclickButtonEvent(boton_siguiente_marcos, boton_anterior_marcos);

        mViewFlipper.setInAnimation(getContext(), android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(getContext(), android.R.anim.fade_out);
        mViewFlipper.setOnTouchListener(new ListenerTouchViewFlipper());

        ImageButton imageButtonHome = (ImageButton) rootView.findViewById(R.id.imageButtonHome);

        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Room_view.class));
            }
        });



        CargarComponetes();

        return rootView;
    }


    private void CargarComponetes(){

        mViewFlipper.removeAllViews();

        ImageView imageView0 = new ImageView(getContext());
        imageView0.setImageResource(Jugador.bed);
        mViewFlipper.addView(imageView0);

        ImageView imageView1 = new ImageView(getContext());
        imageView1.setImageResource(Jugador.trunk);
        mViewFlipper.addView(imageView1);

        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageResource(Jugador.window);
        mViewFlipper.addView(imageView2);

        ImageView imageView3 = new ImageView(getContext());
        imageView3.setImageResource(Jugador.closet);
        mViewFlipper.addView(imageView3);

    }
    private void onclickButtonEvent(ImageButton imageButtonNext,ImageButton imageButtonPrevious){
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.showNext();
            }
        });
        imageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                    }else if(distance<0)
                    {
                        mViewFlipper.showNext();

                    }else{
                        if(MainActivity.mp.isPlaying() || ExplorarHabitacion.mpvoice.isPlaying())
                        {

                            MainActivity.mp.pause();
                            ExplorarHabitacion.mpvoice.pause();
                        }

                        int recurso=mViewFlipper.getDisplayedChild();
                        int numeroAleatorio = (int) (Math.random()*4+1);

                        if(recurso==0){

                            RetoView.idReto=0;
                            if(numeroAleatorio==1){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_1_1_1;

                            }else if(numeroAleatorio==2){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_1_2_1;

                            }else if(numeroAleatorio==3){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_1_3_1;

                            }else{
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_1_4_1;
                            }

                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerView,new VideoOggies()).commit();

                        }else if(recurso==1){
                            RetoView.idReto=1;
                            if(numeroAleatorio==1){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_2_1;

                            }else if(numeroAleatorio==2){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_2_2;

                            }else if(numeroAleatorio==3){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_2_3;

                            }else{
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_2_4;
                            }


                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerView,new VideoOggies()).commit();

                        }else if(recurso==2){
                            RetoView.idReto=2;
                            if(numeroAleatorio==1){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_4_1;

                            }else if(numeroAleatorio==2){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_4_2;

                            }else if(numeroAleatorio==3){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_4_3;

                            }else{
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_4_4;
                            }


                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerView,new VideoOggies()).commit();

                        }else{
                            RetoView.idReto=3;
                            if(numeroAleatorio==1){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_3_1;

                            }else if(numeroAleatorio==2){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_3_2;

                            }else if(numeroAleatorio==3){
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_3_3;

                            }else{
                                VideoOggies.url="android.resource://com.app.mundo.oggies/"+ R.raw.oggie_3_4;
                            }


                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerView,new VideoOggies()).commit();
                        }

                    }


                default:
                    break;
            }
            return false;
        }
    }


}
