package com.example.amcfire.wallmartlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ElectronicDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_detail);
        //set the back (up) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find all our view components
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView titleD = (TextView) findViewById(R.id.title);
        TextView descriptionD = (TextView) findViewById(R.id.description);
        TextView priceD = (TextView) findViewById(R.id.price);
        TextView colorD = (TextView) findViewById(R.id.color);
        TextView idD = (TextView) findViewById(R.id.reference);

        //collect our intent and populate our layout
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String color = intent.getStringExtra("color");
        String id = intent.getStringExtra("id");
        String description = intent.getStringExtra("description");
        Double price = intent.getDoubleExtra("price", 0.0);
        String image = intent.getStringExtra("image");
        Integer imageID = this.getResources().getIdentifier(image, "drawable", this.getPackageName());

        //set elements
        imageView.setImageResource(imageID);
        titleD.setText(title);
        descriptionD.setText(description);
        priceD.setText("Price: $" + Double.toString(price));
        colorD.setText("Color: " + color);
        idD.setText("id: " + id);

        //set the title of this activity to be the street name
        getSupportActionBar().setTitle(title);

    }
}
