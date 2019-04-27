package com.example.amcfire.wallmartlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ClothingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);
        String[] values = getResources().getStringArray(R.array.ClothingProducts);
        ListView lview=(ListView)findViewById(R.id.lview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClothingActivity.this,android.R.layout.simple_spinner_dropdown_item,values);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        });
    }
}
