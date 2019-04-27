package com.nguyenhaidang.lesson8;


import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AudioRecordingFragment extends Fragment implements View.OnClickListener {

    // 1. Create an object for MeadiaRecorder
    MediaRecorder recorder;
    // To display the status of AudioRecording test
    TextView tv;
    // To play the recorded the audio from SDCard
    MediaPlayer mp;
    String path;
    Button btnStart, btnStartPlay;

    boolean isRecording = false;

    public AudioRecordingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio_recording, container, false);
        tv = view.findViewById(R.id.tv1);

        btnStart = view.findViewById(R.id.start);
        btnStartPlay = view.findViewById(R.id.stplay);

        btnStart.setOnClickListener(this);
        btnStartPlay.setOnClickListener(this);

        recorder = new MediaRecorder();
        //2. Set the audio source MIC for recording
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //3. Set the output format with the extension of amr with the high quality NB (WB-low quality) for audio
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        //4. Set the Encoder
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //5. Set the source to store the file
        recorder.setOutputFile("/sdcard/test.amr");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if (!isRecording) {
                    // prepare method need to surround with try catch
                    try {
                        recorder.prepare();
                        tv.setText("Start Recording");
                        recorder.start();
                        btnStart.setText("STOP");
                        isRecording = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("Stop Recording");
                    btnStart.setText("START");
                    recorder.stop();
                    isRecording = false;
                }
                break;
            case R.id.stplay:
                if (mp == null || !mp.isPlaying()) {
                    tv.setText("Playing Audio");
                    mp = new MediaPlayer();
                    try {
                        mp.setDataSource("/sdcard/test.amr");
                        mp.prepare();
                        mp.start();
                        btnStartPlay.setText("STOP PLAY");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("Stopped Playing");
                    mp.stop();
                    mp.release();
                    btnStartPlay.setText("START PLAY");
                }
                break;
        }
    }
}
