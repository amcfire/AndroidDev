package com.example.amcfire.fragmentscvlab;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // Need to use the requirements of the objects for FragmentManager and FragmentTransaction;
    FragmentManager fmanager;
    FragmentTransaction tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assign and get the object for the FragmentManager by using the below statements
        fmanager = getFragmentManager();
        //get the object for FragmentTransaction and Initialize the transaction
        tx = fmanager.beginTransaction();
        /* by default we are going to show the HomeFragment in onCreate() method using add() method
         * add() method accepts two parameters -
         * 1. id of fragment 2.object of Fragment class*/
        tx.add(R.id.frame1,new home());
        // Commit the fragment transaction
        tx.commit();

    }
    public void btnhome(View view){

        tx = fmanager.beginTransaction();
        // when the user selects the Home button, we are replacing the HomeFragment
        tx.replace(R.id.frame1,new home());
        tx.commit();
    }
    public void btnaboutMe(View view){
        tx = fmanager.beginTransaction();
        // when the user selects the Home button, we are replacing the HomeFragment
        tx.replace(R.id.frame1,new AboutMeFragment());
        tx.commit();
    }
    public void btnworkExp(View view){
        tx = fmanager.beginTransaction();
        // when the user selects the Home button, we are replacing the HomeFragment
        tx.replace(R.id.frame1,new workExpFragment());
        tx.commit();
    }
    public void btncontact(View view){
        tx = fmanager.beginTransaction();
        // when the user selects the Home button, we are replacing the HomeFragment
        tx.replace(R.id.frame1,new ContactFragment());
        tx.commit();
    }
}
