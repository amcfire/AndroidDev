package com.example.amcfire.wallmartlab;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BeautyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        String[] values = getResources().getStringArray(R.array.beautyProducts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,values);
        final Spinner spinnerbeauty = (Spinner) findViewById(R.id.beautySpinner);
        spinnerbeauty.setAdapter(adapter);
        spinnerbeauty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Context context = getApplicationContext(); // or you can pass directly this keyword.
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Product "+item+" selected.";
                Toast toast = Toast.makeText(context, text, duration); toast.show();

                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
