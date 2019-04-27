package com.example.amcfire.convertassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TextView tvOutput;
    private EditText evInput;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = (TextView) findViewById(R.id.TextView03);
        evInput = (EditText) findViewById(R.id.editText01);
    }

    public void convert(View view){
        float finput = 0;
        try {
            finput = Float.parseFloat(String.valueOf(evInput.getText()));
        } catch (Exception e){
            tvOutput.setText("Please type a number");
        }
        float result = (float) (finput*(0.453592));
        tvOutput.setText(String.valueOf(result));
    }
}
