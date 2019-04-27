package com.nguyenhaidang.lesson8;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraGalleryFragment extends Fragment implements View.OnClickListener {

    static final int REQUEST_CAPTURE = 1001;
    static final int REQUEST_GALLERY = 1002;

    //  String path = "/storage/sdcard0/test.jpg";
    Button camButton, galleryButton;
    File f;
    Uri file;
    String path;

    ImageView iView;

    public CameraGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_gallery, container, false);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mypicture.jpg";
        camButton = view.findViewById(R.id.camera);
        galleryButton = view.findViewById(R.id.gallery);
        iView = view.findViewById(R.id.iv);

        camButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);
        // If we don’t have the appropriate permissions, we disable the button until we do.
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            camButton.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        return view;
    }

    // Check permission of the camera Intent & External Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                camButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.camera:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CAPTURE);
                break;
            case R.id.gallery:
                i = new Intent();
                // Activity Action for the intent : Pick an item from the data, returning what was selected.
                i.setAction(Intent.ACTION_PICK);
                i.setType("image/*");
                // Start the Gallery Intent activity with the request_code 2
                startActivityForResult(i, REQUEST_GALLERY);
                break;
        }
    }

    // To perform post Activities write your logic in the onActivityResult(), the user actions are determined based on the requestCode
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Intent object data automatically store the selected file path from the Image Gallery from your device storage
        super.onActivityResult(requestCode, resultCode, data);

        // Logic to get from Bundle
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            iView.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_GALLERY) { // For Clicking Gallery button
            // Set the selected image from the device image gallery to the ImageView component
            iView.setImageURI(data.getData());
        }

    }
}
