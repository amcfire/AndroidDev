package com.example.amcfire.implicitintentslab;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void GmailClic(View view){
        et1 = (EditText) findViewById(R.id.editMsg);
        String input = et1.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message");
        emailIntent.putExtra(Intent.EXTRA_TEXT, input);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "No Mail app detected, please configure it before use this app";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
        }
    }

    public void MsgClic(View view){
        et1 = (EditText) findViewById(R.id.editMsg);
        String input = et1.getText().toString();
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("sms_body",input);
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "No MSG app detected, please configure it before use this app";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
        }
    }

    public void WhatsappClic(View view){
        et1 = (EditText) findViewById(R.id.editMsg);
        String input = et1.getText().toString();
        Intent i = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        i.putExtra(Intent.EXTRA_TEXT,input);
        i.setType("text/plain");
        if (i.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(i);
            }catch (android.content.ActivityNotFoundException ex){
                Context context = getApplicationContext(); // or you can pass directly this keyword.
                CharSequence text = "No Whatsapp app detected, please install it before use this app";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); toast.show();
            }
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "No Whatsapp app detected, please install it before use this app";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
        }
    }

    public void HangoutClic(View view){
        et1 = (EditText) findViewById(R.id.editMsg);
        String input = et1.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, input);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.google.android.talk");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }else{
            Context context = getApplicationContext(); // or you can pass directly this keyword.
            CharSequence text = "No Hangouts app detected, please install it before use this app";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration); toast.show();
        }
    }
}
