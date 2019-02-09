package com.app.mundo.oggies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultReto extends AppCompatActivity {

    public static boolean resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Typeface dimbo = Typeface.createFromAsset(getAssets(), "fonts/dimbo.ttf");


        if(resultado){
            if(Jugador.marcosDesbloqueados<6)
            {
                if (Jugador.marcosDesbloqueados == 2) {
                    Jugador.marcos.set(2, R.drawable.marco_foto3);
                } else if (Jugador.marcosDesbloqueados == 3) {
                    Jugador.marcos.set(3, R.drawable.marco_foto4);
                } else if (Jugador.marcosDesbloqueados == 4) {
                    Jugador.marcos.set(4, R.drawable.marco_foto5);
                } else if (Jugador.marcosDesbloqueados == 5) {
                    Jugador.marcos.set(5, R.drawable.marco_foto6);
                }
                Jugador.marcosDesbloqueados++;
            }

            setContentView(R.layout.ganaste);
            TextView txtGanaste = (TextView)findViewById(R.id.id_text_felicitaciones);
            TextView txtDesbloq = (TextView)findViewById(R.id.id_text_desbloqueo);
            TextView txtEsperas = (TextView)findViewById(R.id.id_text_queEsperas);

            txtGanaste.setTypeface(dimbo);
            txtDesbloq.setTypeface(dimbo);
            txtEsperas.setTypeface(dimbo);

            ImageView camara = (ImageView) findViewById(R.id.camara);
            camara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MarcosFotos.class));
                    finish();
                }
            });

        }else{

            setContentView(R.layout.perdiste);

            TextView txtSentimos = (TextView)findViewById(R.id.id_text_losentimos);
            TextView txtIntentando = (TextView)findViewById(R.id.id_text_sigueIntentando);

            txtSentimos.setTypeface(dimbo);
            txtIntentando.setTypeface(dimbo);
            
            ImageView boton_home_perdiste = (ImageView) findViewById(R.id.boton_home_perdiste);
            boton_home_perdiste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Room_view.class));
                    finish();
                }
            });

        }




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

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Room_view.class));
        finish();
    }
}
