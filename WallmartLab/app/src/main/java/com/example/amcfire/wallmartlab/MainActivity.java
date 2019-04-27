package com.example.amcfire.wallmartlab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List <WallmartUser> wusers = new ArrayList<WallmartUser>();
    public WallmartUser currenUser;
    EditText etu;
    EditText etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wusers.add(new WallmartUser("a@mum.edu","111"));
        wusers.add(new WallmartUser("andy@mum.edu","12345"));
        wusers.add(new WallmartUser("dave@mum.edu","12345"));
        wusers.add(new WallmartUser("catalina@mum.edu","12345"));
        wusers.add(new WallmartUser("farruh@mum.edu","12345"));
        wusers.add(new WallmartUser("dael@mum.edu","12345"));
        wusers.add(new WallmartUser("pepe@mum.edu","xyz"));

        SharedPreferences spf = getSharedPreferences("wallmartLab", Context.MODE_PRIVATE);
        String user = spf.getString("user",""); // key, value pair. Here by default name is not found assign " no value"
        String pwd = spf.getString("pass","");
        etu = (EditText)findViewById(R.id.etuser);
        etp = (EditText)findViewById(R.id.etpass);
        etu.setText(user);
        etp.setText(pwd);
    }

    public void forgotPass(View view){
    }

    public void signIn(View view){
        etu = (EditText) findViewById(R.id.etuser);
        etp = (EditText) findViewById(R.id.etpass);
        String inputuser = etu.getText().toString();
        String inputpass = etp.getText().toString();
        currenUser = new WallmartUser(inputuser,inputpass);
        if(validateUser(currenUser)){
            String output = "Sign in to your account";
            TextView tv = (TextView) findViewById(R.id.tv01);
            tv.setText(output);
            String input = currenUser.getWuser();
            Intent intent = new Intent(this,ShoppingActivity.class);
            intent.putExtra("message1","Wellcome "+input); // Here message is a key to retrieve the input text in the second activity
            SharedPreferences spf = getSharedPreferences("wallmartLab", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = spf.edit();
            spe.putString("user",inputuser);
            spe.putString("pass",inputpass);
            spe.commit();
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

    public void newAccount(View view){
        Intent intent=new Intent(MainActivity.this,NewUserActivity.class);
        startActivityForResult(intent, 1);
    }

    public boolean validateUser(WallmartUser wu){
        boolean result = false;
        for (WallmartUser w2:wusers) {
            result = result||w2.verifyUser(wu);
        }
        return result;
    }

    public boolean findUser(String u){
        boolean result = false;
        for (WallmartUser w2:wusers) {
            result = result||w2.getWuser().equals(u);
        }
        return result;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String tuser = data.getStringExtra("msguser");
                String tpass = data.getStringExtra("msgpass");
                if(findUser(tuser)){
                    Context context = getApplicationContext(); // or you can pass directly this keyword.
                    CharSequence text = "User already exists in the system";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration); toast.show();
                }else{
                    WallmartUser nu = new WallmartUser(tuser,tpass);
                    wusers.add(nu);
                }
            }
        }
    }
}
