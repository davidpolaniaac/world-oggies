package com.app.mundo.oggies;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by PC on 16/05/2016.
 */
public class VideoOggies extends Fragment {

    public static String url;
    TextView txtJugar;
    ImageView hulla;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragmento_video_oggies, container, false);
        final VideoView videoView = (VideoView) rootView.findViewById(R.id.videoInit);
        LinearLayout jugar = (LinearLayout) rootView.findViewById(R.id.jugar);
        txtJugar = (TextView) rootView.findViewById(R.id.jugar_video_oggies);
        Typeface dimbo = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/dimbo.ttf");
        txtJugar.setTypeface(dimbo);
        Uri path = Uri.parse(url);

        videoView.setVideoURI(path);

        videoView.start();


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                startActivity(new Intent(getContext(), RetoView.class));
                getActivity().finish();

            }
        });

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), RetoView.class));
                getActivity().finish();

            }
        });


        return rootView;
    }

}
