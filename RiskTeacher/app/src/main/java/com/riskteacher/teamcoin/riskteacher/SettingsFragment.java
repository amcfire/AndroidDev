package com.riskteacher.teamcoin.riskteacher;

import android.app.AlertDialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import database.DatabaseHelper;


public class SettingsFragment extends Fragment {
    String user;
    String balance;
    SimpleDateFormat dateFormat;
    Date date;
    RTOperation opT;
    private DatabaseHelper db;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHelper(getContext());
        SharedPreferences spf = getActivity().getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        user = spf.getString("user","");
        balance = spf.getString("balance","");
        TextView tv1 = getActivity().findViewById(R.id.textViewBal);
        tv1.setText(balance);
        TextView tv2 =  getActivity().findViewById(R.id.tvname);
        tv2.setText(user+"  / ");
        opT=new RTOperation(user);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        refreshBalance();

        Button resetBtn = getActivity().findViewById(R.id.btreset);
        Button delBtn = getActivity().findViewById(R.id.btdelete);
        Button depBtn = getActivity().findViewById(R.id.btdeposit);
        Button emailBtn = getActivity().findViewById(R.id.btemail);
        Button passBtn = getActivity().findViewById(R.id.btpass);
        Button wdBtn = getActivity().findViewById(R.id.btwithdraw);
        Button timerBtn = getActivity().findViewById(R.id.bttimer);
        Button exportBtn = getActivity().findViewById(R.id.btexport);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshBalance();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletehistory();
            }
        });
        depBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makedeposit();
            }
        });
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setemail();
            }
        });
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setpass();
            }
        });
        wdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makewithdraw();
            }
        });
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settimer();
            }
        });
        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exporthistory();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
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

    private BigDecimal getDeposit(){
        BigDecimal ans;
        ans = new BigDecimal(0);
        EditText betsize = (EditText) getActivity().findViewById(R.id.etdeposit);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            if(isNumeric(ssize)){
                ans = new BigDecimal(ssize);
            }else{
                Toast.makeText(getContext(), "Please insert a valid number to Deposit", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Please insert a valid number to Deposit", Toast.LENGTH_LONG).show();
        }
        return ans;
    }

    private BigDecimal getWithdraw(){
        BigDecimal ans;
        ans = new BigDecimal(0);
        EditText betsize = (EditText) getActivity().findViewById(R.id.etwithdraw);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            if(isNumeric(ssize)){
                ans = new BigDecimal(ssize);
            }else{
                Toast.makeText(getContext(), "Please insert a valid number to Withdraw", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Please insert a valid number to Withdraw", Toast.LENGTH_LONG).show();
        }
        return ans;
    }

 /*   private int getRisk(){
        int ans=100;
        EditText betsize = (EditText) getActivity().findViewById(R.id.etrisk);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            if(isNumeric(ssize)){
                ans = Integer.parseInt(ssize);
            }else{
                Toast.makeText(getContext(), "Please insert a valid risk number", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Please insert a valid risk number", Toast.LENGTH_LONG).show();
        }
        return ans;
    }*/

    private String getMail(){
        String ans="";
        EditText betsize = (EditText) getActivity().findViewById(R.id.etemail);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            ans = betsize.getText().toString();
        }else {
            Toast.makeText(getContext(), "Please insert a valid email", Toast.LENGTH_LONG).show();
        }
        return ans;
    }

    private int getTim(){
        int ans=3;
        EditText betsize = (EditText) getActivity().findViewById(R.id.ettimer);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            if(isNumeric(ssize)){
                ans = Integer.parseInt(ssize);
            }else{
                Toast.makeText(getContext(), "Please insert a interval in seconds - integer", Toast.LENGTH_LONG).show();
            }        }else {
            Toast.makeText(getContext(), "Please insert a interval in seconds - integer", Toast.LENGTH_LONG).show();
        }
        return ans;
    }

    private String getPass(){
        String ans="";
        EditText betsize = (EditText) getActivity().findViewById(R.id.etpass);
        String ssize = betsize.getText().toString();
        if(ssize.length()>0){
            ans = betsize.getText().toString();
        }else {
            Toast.makeText(getContext(), "Please insert a valid password", Toast.LENGTH_LONG).show();
        }
        return ans;
    }

    private boolean isNumeric(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void makedeposit(){
        EditText ev1 = (EditText)getActivity().findViewById(R.id.etdeposit);
        BigDecimal n1 = getDeposit();
        BigDecimal n2 = new BigDecimal(db.getBalanceUser(user));
        BigDecimal n3 = n1.add(n2);
        String sn3 = n3.toString();
        db.setBalanceUser(user,sn3);
        SharedPreferences spf = getActivity().getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = spf.edit();
        spe.putString("balance",sn3);
        spe.commit();
        TextView tv1 = getActivity().findViewById(R.id.textViewBal);
        tv1.setText(sn3);
        Toast.makeText(getContext(), "Deposit completed", Toast.LENGTH_LONG).show();
    }

    public void makewithdraw(){
        EditText ev1 = (EditText)getActivity().findViewById(R.id.etdeposit);
        BigDecimal n1 = getWithdraw();
        BigDecimal n2 = new BigDecimal(db.getBalanceUser(user));
        BigDecimal n3 = n2.subtract(n1);
        String sn3 = n3.toString();
        db.setBalanceUser(user,sn3);
        SharedPreferences spf = getActivity().getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = spf.edit();
        spe.putString("balance",sn3);
        spe.commit();
        TextView tv1 = getActivity().findViewById(R.id.textViewBal);
        tv1.setText(sn3);
        Toast.makeText(getContext(), "Withdrawal completed", Toast.LENGTH_LONG).show();
    }

    public void deletehistory(){
        db.delUserOperations(user);
        Toast.makeText(getContext(), "History Deleted", Toast.LENGTH_LONG).show();
    }

    public void exporthistory(){
        ArrayList<RTOperation> list;
        list = (ArrayList<RTOperation>) db.getUserOperations(user);
        if(list.size()>0) {
            RTOperations ops = new RTOperations();
            ops.setOperations(list);

            // Conversion list object to JSON using Gson
            Gson gson = new Gson();
            String response = gson.toJson(ops);

            // Writing the converted data into File using FileWriter in Your device external storage
            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/ops_gson.json";
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(response);
                writer.flush();
                writer.close();
                Toast.makeText(getContext(), "JSON file created on external storage", Toast.LENGTH_LONG).show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else {
            Toast.makeText(getContext(), "Can't Write Empty List", Toast.LENGTH_LONG).show();
        }
    }

    public void resetbalance(){
        db.resetBalanceUser(user);
    }

    public void setemail(){
        String tm = getMail();
        db.setEmailUser(user,tm);
        Toast.makeText(getContext(), "Email updated to "+tm, Toast.LENGTH_LONG).show();
    }

    public void setpass(){
        String tm = getPass();
        db.setPassUser(user,tm);
        Toast.makeText(getContext(), "Password updated to "+tm, Toast.LENGTH_LONG).show();
    }

    public void settimer(){
        int tm = getTim();
        db.setTimerUser(user, tm);
        Toast.makeText(getContext(), "Timer updated to "+tm, Toast.LENGTH_LONG).show();
    }

/*    public void setrisk(){
        String tm = ""+getRisk();
        db.setRiskUser(user,tm);
        Toast.makeText(getContext(), "Risk updated to "+tm, Toast.LENGTH_LONG).show();
    }*/


    @Override
    public void onPause() {
        super.onPause();
        refreshBalance();
    }

    public void refreshBalance(){
        String bal = db.getBalanceUser(user);
        TextView tvbal = (TextView)getActivity().findViewById(R.id.textViewBal);
        tvbal.setText(bal);
        SharedPreferences spf = getActivity().getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = spf.edit();
        spe.putString("balance",bal);
        spe.commit();
        Toast.makeText(getContext(), "Balance updated", Toast.LENGTH_LONG).show();
    }

}
