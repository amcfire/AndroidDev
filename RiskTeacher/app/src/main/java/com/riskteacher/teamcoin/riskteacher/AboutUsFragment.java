package com.riskteacher.teamcoin.riskteacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences spf = getActivity().getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        String user = spf.getString("user","");
        TextView tv2 =  getActivity().findViewById(R.id.tvname);
        tv2.setText(user+"  / ");
    }

    public void logOut(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Exit Confirmation");
        builder.setMessage("Do you want to Exit");
        AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==dialog.BUTTON_POSITIVE){
                    dialog.dismiss(); // dismiss the dialog
                    getActivity().finish(); // to destroy the activity
                }
                else if(which==dialog.BUTTON_NEGATIVE){
                    dialog.dismiss(); // dismiss the dialog, but activity is still alive
                }
            }
        };
        builder.setPositiveButton("Yes",listener);
        builder.setNegativeButton("No",listener);
        builder.show();
    }

}
