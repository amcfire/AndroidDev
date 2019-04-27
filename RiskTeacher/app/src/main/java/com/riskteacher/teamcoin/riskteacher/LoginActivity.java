package com.riskteacher.teamcoin.riskteacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    public List<RTUser> wusers;
    DatabaseHelper dbHelper;
    public RTUser currenUser;
    EditText etu;
    EditText etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        wusers = dbHelper.getAllUsers();
        SharedPreferences spf = getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        String user = spf.getString("user","");
        String pwd = spf.getString("pass","");
        etu = (EditText)findViewById(R.id.etuser);
        etp = (EditText)findViewById(R.id.etpass);
        etu.setText(user);
        etp.setText(pwd);

    }

    @Override
    protected void onResume() {
        super.onResume();
        wusers = dbHelper.getAllUsers();
    }

    public void signIn(View view){
        etu = (EditText) findViewById(R.id.etuser);
        etp = (EditText) findViewById(R.id.etpass);
        String inputuser = etu.getText().toString();
        String inputpass = etp.getText().toString();
        RTUser loginUser = new RTUser(inputuser,inputpass);
        if(validateUser(loginUser)){
            String output = "Sign in to your account";
            TextView tv = (TextView) findViewById(R.id.tv01);
            tv.setText(output);

            SharedPreferences spf = getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = spf.edit();
            //change this part to not use first user, instead use actual user from database
            spe.putString("user",currenUser.getRtuser());
            spe.putString("pass",currenUser.getRtpass());
            spe.putString("email",currenUser.getRtemail());
            String b = dbHelper.getBalanceUser(inputuser);
            spe.putString("balance",b);
            spe.putBoolean("auto",false);
            spe.commit();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "Invalid Credentials, please try again";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
            String output = "Invalid Credentials, please Sign In";
            TextView tv = (TextView) findViewById(R.id.tv01);
            tv.setText(output);
        }
    }

    public boolean validateUser(RTUser wu){
        boolean result = false;
        for (RTUser w2:wusers) {
            result = result||w2.verifyUser(wu);
            if (result){currenUser = w2;}
        }
        return result;
    }

    public void createAccount(View view){
        Intent i = new Intent(this, CreateUser.class);
        startActivityForResult(i, 1);
    }

    public void forgotPassword(View view){
        Intent intent = new Intent(this,ForgotPass.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                String username = data.getStringExtra("username");
                String password = data.getStringExtra("password");

                etu.setText(username);
                etp.setText(password);

            }else if (resultCode == Activity.RESULT_CANCELED){

            }
        }
    }
}
