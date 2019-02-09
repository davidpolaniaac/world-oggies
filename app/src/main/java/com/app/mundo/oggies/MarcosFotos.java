package com.app.mundo.oggies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.app.mundo.oggies.Jugador;
import com.app.mundo.oggies.R;

public class MarcosFotos extends AppCompatActivity implements SurfaceHolder.Callback{

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean cameraview = false;
    LayoutInflater inflater = null;
    ImageView overlay;
    MediaPlayer mpvoice = new MediaPlayer();
    int resIDMarco;
    public static int pos_marco=0;
    TextView textoMarcos;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.marco_foto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface dimbo = Typeface.createFromAsset(getAssets(),  "fonts/dimbo.ttf");

        ImageButton imageButtonHome=(ImageButton) findViewById(R.id.imageButtonHome);
        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Room_view.class));
                finish();
            }
        });



        try
        {
            AssetFileDescriptor afd = getAssets().openFd("marcos.wav");
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

        ImageButton buttonNext = (ImageButton ) findViewById(R.id.boton_siguiente_marcos);
        ImageButton buttonPrevious = (ImageButton ) findViewById(R.id.boton_anterior_marcos);
        textoMarcos = (TextView)findViewById(R.id.txtMarcos);
        textoMarcos.setTypeface(dimbo);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.camaraPreviewMarcos);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        onclickButtonEvent(buttonNext, buttonPrevious);

        //Carga el int del res que se quiere cargar y se asigna al imageView imageOverLay del layout overlay.xml
        overlay = (ImageView)findViewById(R.id.overlayImagePreview);
        resIDMarco = Jugador.marcos.get(pos_marco);
        overlay.setImageResource(resIDMarco);

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resIDMarco==R.drawable.marco_foto2n || resIDMarco==R.drawable.marco_foto3n || resIDMarco==R.drawable.marco_foto4n
                        || resIDMarco==R.drawable.marco_foto5n || resIDMarco==R.drawable.marco_foto6n)
                {
                    Log.e("SOY DIOSSSS","SOY DIOSSS");
                }else
                {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    cameraview = false;
                    startActivity(new Intent(getApplicationContext(), CamaraMarco.class));
                    MainActivity.mp.pause();
                }

            }
        });


    }

    private void onclickButtonEvent(ImageButton imageButtonNext,ImageButton imageButtonPrevious){
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pos_marco<Jugador.marcos.size()-1)
                {
                    pos_marco++;
                    resIDMarco = Jugador.marcos.get(pos_marco);
                    overlay.setImageResource(resIDMarco);
                }

            }
        });
        imageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pos_marco>0)
                {
                    pos_marco--;
                    resIDMarco = Jugador.marcos.get(pos_marco);
                    overlay.setImageResource(resIDMarco);
                }
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        if(cameraview){
            camera.stopPreview();
            cameraview = false;
        }

        if (camera != null){
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                cameraview = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {

                    camera = Camera.open(camIdx);
                    camera.setDisplayOrientation(90);

                } catch (RuntimeException e) {
                    //Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if(camera!=null) {
            camera.stopPreview();
            camera.release();
            camera = null;
            cameraview = false;
        }
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