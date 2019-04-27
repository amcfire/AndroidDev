package com.riskteacher.teamcoin.riskteacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseHelper;

public class ForgotPass extends AppCompatActivity {

    private Button submit_button, send_email;
    private EditText    email_edt;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);


        submit_button = (Button) findViewById(R.id.submit_button);
        email_edt = (EditText) findViewById(R.id.forgot_email_edit_text);
//        send_email = findViewById(R.id.send_email);

        submit_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_edt.getText().toString();
                RTUser user = db.getUser(email);
                if (user != null){
                    Toast.makeText(ForgotPass.this, "Your password is start with : " + user.getRtpass().substring(0,3), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPass.this, "This email have not been registered", Toast.LENGTH_LONG).show();
                }
            }
        });

/*        send_email.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmail = new Intent(Intent.ACTION_VIEW);
                gmail.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");
                gmail.putExtra(Intent.EXTRA_EMAIL, new String[] { "teamcoin@gmail.com" });
                gmail.setData(Uri.parse("teamcoin@gmail.com"));
                gmail.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password Request");
                gmail.setType("message/rfc822");
                gmail.putExtra(Intent.EXTRA_TEXT, "Your request here!");
                startActivity(gmail);
            }
        });*/
    }

}
