package com.example.amcfire.hollywoodac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView testactv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testactv = (AutoCompleteTextView) findViewById(R.id.actv1);
        //String[] values = new String[]{"Citizen Kane", "The Godfather", "Casablanca", "Raging Bull", "Gone with the Wind",
        //        "Lawrence of Arabia", "Vertigo", "The Wizard of Oz", "City Lights", "The Searchers",
        //        "Star Wars", "Psycho", "The Graduate", "The General"};
        String[] values=getResources().getStringArray(R.array.moviesArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,values);
        testactv.setAdapter(adapter);
        testactv.setThreshold(1);
        testactv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getApplicationContext(),"Item selected is : "+parent.getItemIdAtPosition(position),Toast.LENGTH_LONG).show();
                String val = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item selected is : "+val,Toast.LENGTH_LONG).show();
            }
        });

    }
}
