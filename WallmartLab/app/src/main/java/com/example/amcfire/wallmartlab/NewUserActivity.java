package com.example.amcfire.wallmartlab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends AppCompatActivity {
    EditText etf;
    EditText etl;
    EditText etu;
    EditText etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void createNAccount(View view){
        etu = (EditText) findViewById(R.id.etmail);
        etp = (EditText) findViewById(R.id.etpass);
        String inputuser = etu.getText().toString();
        String inputpass = etp.getText().toString();
        if((inputuser.length()>=1)&&(inputpass.length()>=1)) {
            Intent data = new Intent();
            //---set the data to pass back---
            data.putExtra("msguser", inputuser);
            data.putExtra("msgpass", inputpass);

            setResult(RESULT_OK, data);
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "User and pass are not valid";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
        }
        //---close the activity---
        finish();
    }
}
