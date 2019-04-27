package com.nguyenhaidang.lesson8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Fragment fragment;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("Media Player");
        fragment = new MediaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_media_player) {
            toolbar.setTitle("Media Player");
            fragment = new MediaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
        } else if (id == R.id.nav_video) {
            toolbar.setTitle("Video Player");
            fragment = new VideoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
        } else if (id == R.id.nav_audio_recording) {
            toolbar.setTitle("Audio Recording");
            fragment = new AudioRecordingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
        } else if (id == R.id.nav_video_recording) {
            toolbar.setTitle("Video Recording");
            fragment = new VideoRecordingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
        } else if (id == R.id.nav_camera_gallery) {
            toolbar.setTitle("Camera & Gallery");
            fragment = new CameraGalleryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, "fragment").commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
