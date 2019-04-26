package com.example.amcfire.dinnerdecider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvOutput;
    private EditText evInput;
    private Button bt1;
    private List listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFood = new ArrayList<String>();
        listFood.add("Hamburger");
        listFood.add("Pizza");
        listFood.add("Mexican");
        listFood.add("American");
        listFood.add("Chinese");
        evInput = (EditText) findViewById(R.id.editText01);
        tvOutput = (TextView) findViewById(R.id.textView01);
    }

    public void addFood(View view){
        String finput="";
        try {
            finput = String.valueOf(evInput.getText());
        } catch (Exception e){
            tvOutput.setText("Please type your food");
        }
        if(finput.length()<1)
        {
            tvOutput.setText("Please type your food");
        }else {
            if(listFood.contains(finput)){
                tvOutput.setText(String.valueOf(finput));
            }else {
                listFood.add(finput);
                tvOutput.setText(String.valueOf(finput));
            }
        }
    }

    public void decide(View view){
        String finput;
        Random rn = new Random();
        int randomIndex = rn.nextInt(listFood.size());
        tvOutput.setText((CharSequence) listFood.get(randomIndex));
    }
}
