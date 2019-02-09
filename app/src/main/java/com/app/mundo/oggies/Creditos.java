package com.app.mundo.oggies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Creditos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.creditos);


        TextView txtCreditos = (TextView)findViewById(R.id.txtCreditos);
        TextView txtDiseno = (TextView)findViewById(R.id.txtDiseno);
        TextView txtDesarrollo = (TextView)findViewById(R.id.txtDesarrollo);
        TextView txtGise = (TextView)findViewById(R.id.txtGise);
        TextView txtEliza = (TextView)findViewById(R.id.txtEliza);
        TextView txtDavid = (TextView)findViewById(R.id.txtDavid);
        TextView txtFelipe = (TextView)findViewById(R.id.txtFelipe);
        TextView txtAlejo = (TextView)findViewById(R.id.txtAlejo);

        Typeface flamaMedium = Typeface.createFromAsset(getAssets(),  "fonts/flamamedium.otf");
        Typeface dimbo = Typeface.createFromAsset(getAssets(),  "fonts/dimbo.ttf");

        txtCreditos.setTypeface(dimbo);
        txtDiseno.setTypeface(dimbo);
        txtDesarrollo.setTypeface(dimbo);

        txtAlejo.setTypeface(flamaMedium);
        txtDavid.setTypeface(flamaMedium);
        txtFelipe.setTypeface(flamaMedium);
        txtGise.setTypeface(flamaMedium);
        txtEliza.setTypeface(flamaMedium);

        ImageButton ButtonHome = (ImageButton)findViewById(R.id.homeCreditos);
        ButtonHome.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main_parents.class));
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
