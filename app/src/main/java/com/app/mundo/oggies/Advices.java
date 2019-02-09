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

public class Advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.consejos);
        ImageView ButtonHome=(ImageView) findViewById(R.id.id_boton_home_consejos);
        ButtonHome.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main_parents.class));
                finish();
            }
        });

        Typeface flamaMedium = Typeface.createFromAsset(getAssets(),  "fonts/flamamedium.otf");
        TextView consejoTitulo1 = (TextView)findViewById(R.id.consejos_titulo_1);
        TextView consejoTitulo2 = (TextView)findViewById(R.id.consejos_titulo_2);
        TextView consejoTitulo3 = (TextView)findViewById(R.id.consejos_titulo_3);
        TextView consejoTitulo4 = (TextView)findViewById(R.id.consejos_titulo_4);
        TextView consejoTitulo5 = (TextView)findViewById(R.id.consejos_titulo_5);
        TextView consejoTitulo6 = (TextView)findViewById(R.id.consejos_titulo_6);
        TextView consejoTitulo7 = (TextView)findViewById(R.id.consejos_titulo_7);
        TextView consejoTitulo8 = (TextView)findViewById(R.id.consejos_titulo_8);

        TextView consejoTexto1 = (TextView)findViewById(R.id.consejos_texto_1);
        TextView consejoTexto2 = (TextView)findViewById(R.id.consejos_texto_2);
        TextView consejoTexto3 = (TextView)findViewById(R.id.consejos_texto_3);
        TextView consejoTexto4 = (TextView)findViewById(R.id.consejos_texto_4);
        TextView consejoTexto5 = (TextView)findViewById(R.id.consejos_texto_5);
        TextView consejoTexto6 = (TextView)findViewById(R.id.consejos_texto_6);
        TextView consejoTexto7 = (TextView)findViewById(R.id.consejos_texto_7);
        TextView consejoTexto8 = (TextView)findViewById(R.id.consejos_texto_8);

        consejoTitulo1.setTypeface(flamaMedium);
        consejoTitulo2.setTypeface(flamaMedium);
        consejoTitulo3.setTypeface(flamaMedium);
        consejoTitulo4.setTypeface(flamaMedium);
        consejoTitulo5.setTypeface(flamaMedium);
        consejoTitulo6.setTypeface(flamaMedium);
        consejoTitulo7.setTypeface(flamaMedium);
        consejoTitulo8.setTypeface(flamaMedium);

        consejoTexto1.setTypeface(flamaMedium);
        consejoTexto2.setTypeface(flamaMedium);
        consejoTexto3.setTypeface(flamaMedium);
        consejoTexto4.setTypeface(flamaMedium);
        consejoTexto5.setTypeface(flamaMedium);
        consejoTexto6.setTypeface(flamaMedium);
        consejoTexto7.setTypeface(flamaMedium);
        consejoTexto8.setTypeface(flamaMedium);


    }

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
