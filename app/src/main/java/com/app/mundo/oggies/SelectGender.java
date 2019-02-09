package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectGender extends AppCompatActivity {

    MediaPlayer mpvoice = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.genre_select);
        LinearLayout ButtonBoy = (LinearLayout) findViewById(R.id.id_boton_nino);
        LinearLayout ButtonGirl = (LinearLayout) findViewById(R.id.id_boton_nina);
        TextView titulo = (TextView) findViewById(R.id.titulo_que_eres);
        TextView txtBtnNino = (TextView) findViewById(R.id.txt_btn_nino);
        TextView txtBtnNina = (TextView) findViewById(R.id.txt_btn_nina);

        ImageView imgNino = (ImageView)findViewById(R.id.id_imagen_nino);
        ImageView imgNina = (ImageView)findViewById(R.id.id_imagen_nina);


        Typeface dimbo = Typeface.createFromAsset(getAssets(),  "fonts/dimbo.ttf");
        titulo.setTypeface(dimbo);
        txtBtnNino.setTypeface(dimbo);
        txtBtnNina.setTypeface(dimbo);


        ButtonBoy.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CharacterEditor.class));
                CharacterEditor.personaje = new Boy();


            }
        });

        imgNino.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CharacterEditor.class));
                CharacterEditor.personaje = new Boy();


            }
        });

        ButtonGirl.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CharacterEditor.class));
                CharacterEditor.personaje = new Girl();

            }
        });

        imgNina.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CharacterEditor.class));
                CharacterEditor.personaje = new Girl();


            }
        });

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("nino_o_nina.wav");
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
}