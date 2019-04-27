package com.nguyenhaidang.lesson8;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediaFragment extends Fragment implements View.OnClickListener {


    MediaPlayer mPlayer;
    SeekBar sBar;
    android.os.Handler handler = new android.os.Handler();
    ImageView backward, play, stop, forward;

    public MediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        sBar = view.findViewById(R.id.sBar);
        backward = view.findViewById(R.id.backward);
        play = view.findViewById(R.id.play);
        stop = view.findViewById(R.id.stop);
        forward = view.findViewById(R.id.forward);

        backward.setOnClickListener(this);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        forward.setOnClickListener(this);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mPlayer = MediaPlayer.create(getActivity(), R.raw.saripovu);
        sBar.setMax(mPlayer.getDuration());
        updateSeekBar();
        return view;
    }

    public void updateSeekBar() {
        sBar.setProgress(mPlayer.getCurrentPosition());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        }, 5000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backward:
                mPlayer.seekTo(mPlayer.getCurrentPosition() - mPlayer.getDuration() / 10);
                break;

            case R.id.play:
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                    play.setImageResource(R.drawable.pause);
                } else {
                    mPlayer.pause();
                    play.setImageResource(R.drawable.logo);
                }
                break;
            case R.id.stop:
                mPlayer.stop();
                mPlayer = MediaPlayer.create(getActivity(), R.raw.saripovu);
                play.setImageResource(R.drawable.logo);
                sBar.setProgress(0);
                break;
            case R.id.forward:
                mPlayer.seekTo(mPlayer.getCurrentPosition() + mPlayer.getDuration() / 10);
                break;
        }
    }
}
