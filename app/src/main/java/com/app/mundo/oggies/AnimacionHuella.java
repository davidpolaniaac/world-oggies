package com.app.mundo.oggies;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;


public class AnimacionHuella extends Fragment {
    boolean inicioAnimacion;
    ImageView hulla;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_animacion_huella, container, false);
        final VideoView videoView = (VideoView) rootView.findViewById(R.id.videoInit);
        Uri path = Uri.parse("android.resource://com.app.mundo.oggies/"+ R.raw.huellafinal);
        videoView.setVideoURI(path);
        inicioAnimacion=false;
        videoView.start();
        videoView.postDelayed(new Runnable() {

            @Override
            public void run() {
                videoView.pause();

            }
        }, 1000);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                startActivity(new Intent(getContext(), SelectGender.class));
                MainActivity.mp.pause();
                getActivity().finish();
            }
        });
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                videoView.start();
                return true;
            }
        });


        return rootView;
    }
}
