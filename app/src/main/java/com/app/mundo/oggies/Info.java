package com.app.mundo.oggies;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    boolean continueMusic = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.informacion);
        ImageView ButtonHome=(ImageView) findViewById(R.id.id_boton_home);
        ButtonHome.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main_parents.class));
                finish();
            }
        });

        Typeface flamaMedium = Typeface.createFromAsset(getAssets(),  "fonts/flamamedium.otf");

        TextView infoTitulo1 = (TextView)findViewById(R.id.info_titulo_1);
        TextView infoTitulo2 = (TextView)findViewById(R.id.info_titulo_2);
        TextView infoTitulo3 = (TextView)findViewById(R.id.info_titulo_3);
        TextView infoTitulo4 = (TextView)findViewById(R.id.info_titulo_4);
        TextView infoTitulo5 = (TextView)findViewById(R.id.info_titulo_5);
        TextView infoTitulo6 = (TextView)findViewById(R.id.info_titulo_6);

        TextView infoTexto1 = (TextView)findViewById(R.id.info_texto_1);
        TextView infoTexto2 = (TextView)findViewById(R.id.info_texto_2);
        TextView infoTexto3 = (TextView)findViewById(R.id.info_texto_3);
        TextView infoTexto4 = (TextView)findViewById(R.id.info_texto_4);
        TextView infoTexto5 = (TextView)findViewById(R.id.info_texto_5);
        TextView infoTexto6 = (TextView)findViewById(R.id.info_texto_6);

        infoTitulo1.setTypeface(flamaMedium);
        infoTitulo2.setTypeface(flamaMedium);
        infoTitulo3.setTypeface(flamaMedium);
        infoTitulo4.setTypeface(flamaMedium);
        infoTitulo5.setTypeface(flamaMedium);
        infoTitulo6.setTypeface(flamaMedium);

        infoTexto1.setTypeface(flamaMedium);
        infoTexto2.setTypeface(flamaMedium);
        infoTexto3.setTypeface(flamaMedium);
        infoTexto4.setTypeface(flamaMedium);
        infoTexto5.setTypeface(flamaMedium);
        infoTexto6.setTypeface(flamaMedium);




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
        if(!MainActivity.mp.isPlaying())
        {
            MainActivity.mp.start();
        }

    }
}
