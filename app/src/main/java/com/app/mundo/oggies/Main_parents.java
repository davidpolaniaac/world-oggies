package com.app.mundo.oggies;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main_parents extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.main_parents);
        ImageButton ButtonInfo=(ImageButton) findViewById(R.id.id_boton_informacion);
        ImageButton ButtonAdvice=(ImageButton) findViewById(R.id.id_boton_consejos);
        ImageButton ButtonHome=(ImageButton) findViewById(R.id.home_main_parents);
        ImageButton btnCreditos=(ImageButton) findViewById(R.id.btnCreditos);

        TextView info =(TextView) findViewById(R.id.id_titulo_informacion);
        TextView advice =(TextView) findViewById(R.id.id_titulo_consejos);
        TextView creditos =(TextView) findViewById(R.id.titleCreditos);

        Typeface flamaMedium = Typeface.createFromAsset(getAssets(),  "fonts/flamamedium.otf");
        info.setTypeface(flamaMedium);
        advice.setTypeface(flamaMedium);
        creditos.setTypeface(flamaMedium);

        ButtonInfo.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Info.class));
            }
        });

        btnCreditos.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Creditos.class));
            }
        });
        info.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Info.class));
            }
        });


        ButtonAdvice.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Advices.class));
            }
        });
        advice.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Advices.class));
            }
        });
        creditos.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Creditos.class));
            }
        });

        ButtonHome.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        });

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
