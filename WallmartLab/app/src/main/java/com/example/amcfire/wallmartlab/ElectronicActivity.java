package com.example.amcfire.wallmartlab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ElectronicActivity extends AppCompatActivity {

    private ArrayList<electronic> eList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);

        //create our property elements
        eList.add(new electronic("10", "Smart TV Samsung XD", "Black", "LED 45 inches, Netflix, Google, USA compatible", "tv", 350.00));
        eList.add(new electronic("11", "Laptop HP Pavillon 15", "Silver", "RAM 8GB, Intel Core I7, 1TB DD", "laptop", 400.00));
        eList.add(new electronic("12", "Smartphone OnePlus 9", "Gold", "Android Oreo, Snapdragon, 3GB, dual camera", "phone", 500.00));
        eList.add(new electronic("13", "Printer HP LX8000", "Black", "Printer, scanner, multifunctional, low cost suplies", "printer", 200.50));
        eList.add(new electronic("14", "Tablet Samsung Note Plus", "Silver", "10 inches, quadcore, android lollipop, wifi, 3G", "tablet", 90.30));

        //create our new array adapter
        ArrayAdapter<electronic> adapter = new ElectronicActivity.ElectronicArrayAdapter(this, 0, eList);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                electronic f1 = eList.get(position);
                Intent intent = new Intent(ElectronicActivity.this, ElectronicDetailActivity.class);
                intent.putExtra("id", f1.getId());
                intent.putExtra("color", f1.getColor());
                intent.putExtra("title", f1.getTitle());
                intent.putExtra("description", f1.getDescription());
                intent.putExtra("image", f1.getImage());
                intent.putExtra("price", f1.getPrice());

                startActivityForResult(intent, 1000);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);



    }

    //custom ArrayAdapter
    class ElectronicArrayAdapter extends ArrayAdapter<electronic>{

        private Context context;
        private List<electronic> electronicList;

        //constructor, call on creation
        public ElectronicArrayAdapter(Context context, int resource, ArrayList<electronic> objects) {
            super(context, resource, objects);
            this.context = context;
            this.electronicList = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {
            //get the property we are displaying
            electronic electronic1 = electronicList.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //conditionally inflate either standard(property_layout) or special template(property_layout_alt
            View view;
            view = inflater.inflate(R.layout.electronic_layer, null);

            TextView electronicname = (TextView) view.findViewById(R.id.electronicname);
            TextView description = (TextView) view.findViewById(R.id.description);
            TextView color = (TextView) view.findViewById(R.id.color);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            electronicname.setText(electronic1.getTitle());
            description.setText(electronic1.getDescription());
            price.setText("$" + String.valueOf(electronic1.getPrice()));
            color.setText("unit: " + String.valueOf(electronic1.getColor()));
            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(electronic1.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);
            return view;
        }
    }


}
