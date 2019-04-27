package com.nguyenhaidang.lesson8;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        // Play from Internet
        Uri uri = Uri.parse("https://ia800201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4");
        VideoView vView = (VideoView) view.findViewById(R.id.videoView);
        // Set the path from the external storage
        //   vView.setVideoPath("/sdcard/waterfall.mp4");
        vView.setVideoURI(uri);
        // Start playing Video
        vView.start();
        // To get the seekbar, pause, play, forward and backward control
        MediaController mc = new MediaController(getActivity());
        // Set the MediaController to the VideoView
        vView.setMediaController(mc);
        return view;
    }

}
