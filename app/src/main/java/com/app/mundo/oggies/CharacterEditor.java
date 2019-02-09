package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class CharacterEditor extends AppCompatActivity {
    public static Personaje personaje;
    private ViewFlipper mHeadFlipper;
    private ViewFlipper mBodyFlipper;
    private ViewFlipper mAccessoryFlipper;
    private ImageView head;
    private ImageView body;
    private ImageView accesory;
    public float init_x;
    Estacion estacion;
    HashTableHead hashHead= new HashTableHead();
    private MediaPlayer mpvoice1 = new MediaPlayer();
    private MediaPlayer mpvoice2 = new MediaPlayer();
    LinearLayout base;
    ImageButton buttonSummer;
    ImageButton buttonSpring;
    ImageButton buttonWinter;
    ImageButton buttonAutumn;
    ImageButton imageButtonCancel;
    int posicionEstacion=0; //0 = verano, 1= otono, 2= invierno, 3 = primavera


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.edit_character);

        estacion=personaje.getSummer();

        ImageView accepted=(ImageView)findViewById(R.id.imageButtonAccepted);

        buttonSummer = (ImageButton) findViewById(R.id.imageButtonSummer);
        buttonSpring = (ImageButton) findViewById(R.id.imageButtonSpring);
        buttonWinter = (ImageButton) findViewById(R.id.imageButtonWinter);
        buttonAutumn = (ImageButton) findViewById(R.id.imageButtonAutumn);

        base=(LinearLayout) findViewById(R.id.base);
        base.setBackgroundResource(personaje.base);

        head=(ImageView) findViewById(R.id.head_select);
        body=(ImageView) findViewById(R.id.body_select);
        accesory=(ImageView) findViewById(R.id.accesorie_select);

        mHeadFlipper = (ViewFlipper) findViewById(R.id.id_flipper_head);
        mBodyFlipper = (ViewFlipper) findViewById(R.id.id_flipper_body);
        mAccessoryFlipper = (ViewFlipper) findViewById(R.id.id_flipper_accesories);

        ImageButton buttonNextHead = (ImageButton ) findViewById(R.id.buttonNextHead);
        ImageButton buttonPreviousHead = (ImageButton ) findViewById(R.id.buttonPreviousHead);

        ImageButton buttonNextBody = (ImageButton ) findViewById(R.id.buttonNextBody);
        ImageButton buttonPreviousBody = (ImageButton ) findViewById(R.id.buttonPreviousBody);

        ImageButton buttonNextAccessory = (ImageButton ) findViewById(R.id.buttonNextAccesory);
        ImageButton buttonPreviousAccessory = (ImageButton ) findViewById(R.id.buttonPreviousAccesoty);

        onclickButtonEstacion(buttonSummer, buttonSpring, buttonWinter, buttonAutumn);

        mHeadFlipper.setInAnimation(this, android.R.anim.fade_in);
        mHeadFlipper.setOutAnimation(this, android.R.anim.fade_out);
        mBodyFlipper.setInAnimation(this, android.R.anim.fade_in);
        mBodyFlipper.setOutAnimation(this, android.R.anim.fade_out);
        mAccessoryFlipper.setInAnimation(this, android.R.anim.fade_in);
        mAccessoryFlipper.setOutAnimation(this, android.R.anim.fade_out);

        onclickButtonEvent(buttonNextHead, buttonPreviousHead, mHeadFlipper);
        onclickButtonEvent(buttonNextBody, buttonPreviousBody, mBodyFlipper);
        onclickButtonEvent(buttonNextAccessory, buttonPreviousAccessory, mAccessoryFlipper);

        imageButtonCancel=(ImageButton) findViewById(R.id.imageButtonCancel);

        imageButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Jugador.creado==false){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), Room_view.class));
                    finish();
                }

            }
        });

        CargarComponetes();

        // Valores por default del personaje cuando se crea por primera vez, hay que cambiarlo en el momento de que se guarde
        /*Jugador.head=personaje.base;
        Jugador.body=0;
        Jugador.accessory=0;*/
        if(Jugador.head==0 || Jugador.head==personaje.base)
        {

            head.setBackgroundResource(personaje.base);
            Jugador.head = personaje.base;
        }
        else
        {
            int recurso = Jugador.headRecurso;
            int elemento = hashHead.hashtableHead.get(recurso);
            base.setBackgroundResource(elemento);
        }
        //head.setBackgroundResource(Jugador.head);
        if(Jugador.body!=0) {
            body.setBackgroundResource(Jugador.body);
        }

        if(Jugador.accessory!=0){
            accesory.setBackgroundResource(Jugador.accessory);
        }

        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Jugador.estacion = estacion.getBackgroundResource();
                startActivity(new Intent(getApplicationContext(), Bienvenido.class));

            }
        });

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("estaciones.wav");
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

                    AssetFileDescriptor afd = getAssets().openFd("edicion_personaje.wav");
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



    }

    private void CargarComponetes(){

        mHeadFlipper.removeAllViews();
        mBodyFlipper.removeAllViews();
        mAccessoryFlipper.removeAllViews();

        mHeadFlipper.setOnTouchListener(new ListenerTouchViewFlipperHead(mHeadFlipper, base, estacion.getHead()));
        mBodyFlipper.setOnTouchListener(new ListenerTouchViewFlipperBody(mBodyFlipper,body,estacion.getBody()));
        mAccessoryFlipper.setOnTouchListener(new ListenerTouchViewFlipperAccessory(mAccessoryFlipper,accesory,estacion.getAccessory()));

        for (int i = 0; i < personaje.idEstacion(estacion.getIdEstacion()).getHead().size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(estacion.getHead().get(i));
            mHeadFlipper.addView(imageView);
        }
        for (int i = 0; i < personaje.idEstacion(estacion.getIdEstacion()).getBody().size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(estacion.getBody().get(i));
            mBodyFlipper.addView(imageView);
        }
        for (int i = 0; i < personaje.idEstacion(estacion.getIdEstacion()).getAccessory().size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(estacion.getAccessory().get(i));
            mAccessoryFlipper.addView(imageView);
        }

    }
    private void onclickButtonEstacion(final ImageView summer, final ImageView spring, final ImageView winter, final ImageView autumn){

        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estacion=personaje.getSummer();
                posicionEstacion=0;
                calcularBotones();
                CargarComponetes();
            }
        });
        autumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estacion = personaje.getAutumn();
                posicionEstacion=1;
                calcularBotones();
                CargarComponetes();
            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estacion=personaje.getWinter();
                posicionEstacion=2;
                calcularBotones();
                CargarComponetes();
            }
        });
        spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estacion=personaje.getSpring();
                posicionEstacion=3;
                calcularBotones();
                CargarComponetes();
            }
        });
    }
    private void onclickButtonEvent(ImageButton imageButtonNext,ImageButton imageButtonPrevious, final ViewFlipper viewFlipper){
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
        imageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
    }
    private class ListenerTouchViewFlipperBody implements View.OnTouchListener{

        ViewFlipper viewFlipper;
        ImageView imageView;
        ArrayList<Integer> elementos;

        public ListenerTouchViewFlipperBody(ViewFlipper viewFlipper,ImageView imageView,ArrayList<Integer> elementos){
            this.viewFlipper=viewFlipper;
            this.imageView=imageView;
            this.elementos=elementos;
        }
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
                        viewFlipper.showPrevious();

                    }else if(distance<0)
                    {
                        viewFlipper.showNext();

                    }else{

                        int elemento  = elementos.get(viewFlipper.getDisplayedChild());
                        imageView.setBackgroundResource(elemento);
                        Jugador.body=elemento;

                    }


                default:
                    break;
            }
            return false;
        }
    }
    private class ListenerTouchViewFlipperHead implements View.OnTouchListener{

        ViewFlipper viewFlipper;
        LinearLayout imageView;
        ArrayList<Integer> elementos;

        public ListenerTouchViewFlipperHead(ViewFlipper viewFlipper,LinearLayout imageView,ArrayList<Integer> elementos){
            this.viewFlipper=viewFlipper;
            this.imageView=imageView;
            this.elementos=elementos;
        }
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
                        viewFlipper.showPrevious();

                    }else if(distance<0)
                    {
                        viewFlipper.showNext();

                    }else{

                        int recurso = elementos.get(viewFlipper.getDisplayedChild());
                        int elemento = hashHead.hashtableHead.get(recurso);
                        imageView.setBackgroundResource(elemento);
                        Jugador.head=elemento;
                        Jugador.headRecurso=recurso;

                    }


                default:
                    break;
            }
            return false;
        }
    }
    private class ListenerTouchViewFlipperAccessory implements View.OnTouchListener{

        ViewFlipper viewFlipper;
        ImageView imageView;
        ArrayList<Integer> elementos;

        public ListenerTouchViewFlipperAccessory(ViewFlipper viewFlipper,ImageView imageView,ArrayList<Integer> elementos){
            this.viewFlipper=viewFlipper;
            this.imageView=imageView;
            this.elementos=elementos;
        }
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
                        viewFlipper.showPrevious();

                    }else if(distance<0)
                    {
                        viewFlipper.showNext();

                    }else{


                        int elemento  = elementos.get(viewFlipper.getDisplayedChild());
                        imageView.setBackgroundResource(elemento);
                        Jugador.accessory=elemento;

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
        if(posicionEstacion==0)
        {
            buttonSummer.setBackgroundResource(R.drawable.boton_verano_2);
            buttonAutumn.setBackgroundResource(R.drawable.boton_otono1);
            buttonWinter.setBackgroundResource(R.drawable.boton_invierno_1);
            buttonSpring.setBackgroundResource(R.drawable.boton_primavera_1);
        }
        //Otono seleccionado
        else if(posicionEstacion==1)
        {
            buttonSummer.setBackgroundResource(R.drawable.boton_verano_1);
            buttonAutumn.setBackgroundResource(R.drawable.boton_otono2);
            buttonWinter.setBackgroundResource(R.drawable.boton_invierno_1);
            buttonSpring.setBackgroundResource(R.drawable.boton_primavera_1);
        }
        //Invierno seleccionado
        else if(posicionEstacion==2)
        {
            buttonSummer.setBackgroundResource(R.drawable.boton_verano_1);
            buttonAutumn.setBackgroundResource(R.drawable.boton_otono1);
            buttonWinter.setBackgroundResource(R.drawable.boton_invierno_2);
            buttonSpring.setBackgroundResource(R.drawable.boton_primavera_1);
        }
        //Primavera seleccionada
        else if(posicionEstacion==3)
        {
            buttonSummer.setBackgroundResource(R.drawable.boton_verano_1);
            buttonAutumn.setBackgroundResource(R.drawable.boton_otono1);
            buttonWinter.setBackgroundResource(R.drawable.boton_invierno_1);
            buttonSpring.setBackgroundResource(R.drawable.boton_primavera2);
        }
    }

    @Override
    public void onBackPressed() {
        if(Jugador.creado==false){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(getApplicationContext(), Room_view.class));
            finish();
        }
    }
}
