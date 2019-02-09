package com.app.mundo.oggies;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnimacionInicial extends AppCompatActivity {

    public android.support.v4.app.FragmentManager mFragmentManager;
    public android.support.v4.app.FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion_inicial);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new VideoInicial()).commit();

    }
}
