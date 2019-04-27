package com.riskteacher.teamcoin.riskteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseHelper;

public class CreateUser extends AppCompatActivity {

    private EditText username_edittext, email_edittext, password_edittext;
    private Button create_button;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        username_edittext = findViewById(R.id.username_edittext);
        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        create_button = findViewById(R.id.create_button);
        db = new DatabaseHelper(this);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username_edittext.getText()) ||
                        TextUtils.isEmpty(email_edittext.getText()) ||
                        TextUtils.isEmpty(password_edittext.getText()))
                    Toast.makeText(CreateUser.this, "All fields must not be blank!!!", Toast.LENGTH_LONG).show();
                else {
                    RTUser newUser = new RTUser(username_edittext.getText().toString(), password_edittext.getText().toString());
                    newUser.setRtemail(email_edittext.getText().toString());
                    long x = db.insertUser(newUser);
                    if(x>0){
                        db.setBalanceUser(newUser.getRtuser(),"1000");
                        Intent resultIntent = new Intent();
                        String username = username_edittext.getText().toString();
                        String password = password_edittext.getText().toString();
                        resultIntent.putExtra("username", username);
                        resultIntent.putExtra("password", password);
                        setResult(RESULT_OK, resultIntent);
                        Toast.makeText(CreateUser.this, "Account created successfully!!!", Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(CreateUser.this, "User Already exists in database", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
