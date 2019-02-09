package com.app.mundo.oggies;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;


public class VideoInicial extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_video_inicial, container, false);
        ImageButton exitvideo;
        exitvideo=(ImageButton) rootView.findViewById(R.id.buttonExitVideo);
        final VideoView videoView = (VideoView) rootView.findViewById(R.id.videoInit);
        Uri path = Uri.parse("android.resource://com.app.mundo.oggies/"+ R.raw.ini);
        videoView.setVideoURI(path);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView,new AnimacionHuella()).commit();
            }
        });
        exitvideo.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView,new AnimacionHuella()).commit();
            }
        });

        return rootView;

    }
}
