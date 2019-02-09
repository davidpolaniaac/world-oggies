package com.app.mundo.oggies;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExplorarHabitacion extends AppCompatActivity {

    public android.support.v4.app.FragmentManager mFragmentManager;
    public android.support.v4.app.FragmentTransaction mFragmentTransaction;
    public static MediaPlayer mpvoice = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion_inicial);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new ExplorarElementos()).commit();


        try
        {
            AssetFileDescriptor afd = getAssets().openFd("secretos.wav");
            mpvoice.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
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

    @Override
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

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Room_view.class));
        finish();
    }
}
