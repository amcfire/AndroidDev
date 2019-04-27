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

public class FoodActivity extends AppCompatActivity {

    private ArrayList<fruit> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //create our property elements
        foodList.add(new fruit(10, "apple", "Lbs", "Red Apples", "apple", 4.00));
        foodList.add(new fruit(11, "banana", "Lbs", "Tropical banana", "banana", 8.00));
        foodList.add(new fruit(12, "grape", "Lbs", "Green Grapes", "grape", 5.00));
        foodList.add(new fruit(13, "kiwi", "Lbs", "Green Kiwi", "kiwi", 13.50));
        foodList.add(new fruit(14, "orange", "Lbs", "Tangelo Oranges", "orange", 3.30));
        foodList.add(new fruit(15, "strawberry", "Lbs", "Red Apples", "strawberry", 4.20));

        //create our new array adapter
        ArrayAdapter<fruit> adapter = new foodArrayAdapter(this, 0, foodList);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fruit f1 = foodList.get(position);
                String item = "Food: "+f1.getFoodName().toString()+" selected";
                Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);



    }

    //custom ArrayAdapter
    class foodArrayAdapter extends ArrayAdapter<fruit>{

        private Context context;
        private List<fruit> fruitList;

        //constructor, call on creation
        public foodArrayAdapter(Context context, int resource, ArrayList<fruit> objects) {
            super(context, resource, objects);
            this.context = context;
            this.fruitList = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {
            //get the property we are displaying
            fruit fruit1 = fruitList.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //conditionally inflate either standard(property_layout) or special template(property_layout_alt
            View view;
            view = inflater.inflate(R.layout.fruit_layer, null);

            TextView fruitname = (TextView) view.findViewById(R.id.fruitname);
            TextView description = (TextView) view.findViewById(R.id.description);
            TextView unit = (TextView) view.findViewById(R.id.unit);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            fruitname.setText(fruit1.getFoodName());
            description.setText(fruit1.getDescription());
            price.setText("$" + String.valueOf(fruit1.getPrice()));
            unit.setText("unit: " + String.valueOf(fruit1.getFoodUnit()));
            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(fruit1.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);
            return view;
        }
    }

}
