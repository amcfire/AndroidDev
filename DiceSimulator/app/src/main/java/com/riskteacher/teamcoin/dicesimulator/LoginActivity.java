package com.riskteacher.teamcoin.dicesimulator;

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

public class LoginActivity extends AppCompatActivity {

    public List<RTUser> wusers = new ArrayList<RTUser>();
    public RTUser currenUser;
    EditText etu;
    EditText etp;
    RTUser firstUser = new RTUser("u","p");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // first user creation
        firstUser.setRtbalance(new BigDecimal(1000));
        firstUser.setRtemail("acorchuelo@mum.edu");

        // add user to temp storage
        wusers.add(firstUser);

        SharedPreferences spf = getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
        String user = spf.getString("user","");
        String pwd = spf.getString("pass","");
        etu = (EditText)findViewById(R.id.etuser);
        etp = (EditText)findViewById(R.id.etpass);
        etu.setText(user);
        etp.setText(pwd);

    }

    public void signIn(View view){
        etu = (EditText) findViewById(R.id.etuser);
        etp = (EditText) findViewById(R.id.etpass);
        String inputuser = etu.getText().toString();
        String inputpass = etp.getText().toString();
        currenUser = new RTUser(inputuser,inputpass);
        if(validateUser(currenUser)){
            String output = "Sign in to your account";
            TextView tv = (TextView) findViewById(R.id.tv01);
            tv.setText(output);

            SharedPreferences spf = getSharedPreferences("RiskTeacher", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = spf.edit();
            //change this part to not use first user, instead use actual user from database
            spe.putString("user",firstUser.getRtuser());
            spe.putString("pass",firstUser.getRtpass());
            spe.putString("email",firstUser.getRtemail());
            spe.putString("balance",firstUser.getRtbalance().toString());
            spe.putBoolean("auto",false);
            spe.commit();

            Intent intent = new Intent(this,DiceActivity.class);
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
        }
        return result;
    }
}
