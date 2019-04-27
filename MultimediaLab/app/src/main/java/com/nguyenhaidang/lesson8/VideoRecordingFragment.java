package com.nguyenhaidang.lesson8;


import android.Manifest;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoRecordingFragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {


    MediaRecorder recorder;

    SurfaceView sView;

    SurfaceHolder sHolder;
    Button st, sp;

    public VideoRecordingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_recording, container, false);

        st = (Button) view.findViewById(R.id.button);
        sp = (Button) view.findViewById(R.id.button2);
        sView = (SurfaceView) view.findViewById(R.id.surfaceView);

        st.setOnClickListener(this);
        sp.setOnClickListener(this);
        sHolder = sView.getHolder();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            st.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 0);
        }

        return view;
    }

    // Check permission of the camera Intent & External Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                st.setEnabled(true);
            }
        }
    }

    public void init() {

        try {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
            recorder.setProfile(profile);
            String vFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/videotest.mp4";
            recorder.setOutputFile(vFilePath);
            recorder.setPreviewDisplay(sHolder.getSurface());
            recorder.prepare();
        } catch (Exception e) {

        }
    }


    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(getActivity(), permissionType);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permissionType}, requestCode);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        recorder.setPreviewDisplay(surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                init();
                recorder.start();
                break;
            case R.id.button2:
                recorder.stop();
                break;
        }
    }
}
