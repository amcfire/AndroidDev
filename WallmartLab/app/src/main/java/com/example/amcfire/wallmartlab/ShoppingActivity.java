package com.example.amcfire.wallmartlab;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Intent intent2 = getIntent();
        String output = intent2.getStringExtra("message1");
        TextView tv = (TextView) findViewById(R.id.tvwellcome);
        tv.setText(output);


        ActionBar aBar=getSupportActionBar();
        aBar.setTitle("Shopping Options");
        aBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener listener=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                String selection = tab.getText().toString();
                if(selection.equals("Shopping")){
                    Toast.makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_LONG).show();
                }else if(selection.equals("Electronics")){
                    Intent intent1 = new Intent(getApplicationContext(),ElectronicActivity.class);
                    startActivity(intent1);
                }else if(selection.equals("Food")){
                    Intent intent1 = new Intent(getApplicationContext(),FoodActivity.class);
                    startActivity(intent1);
                }else if(selection.equals("Beauty")){
                    Intent intent1 = new Intent(getApplicationContext(),BeautyActivity.class);
                    startActivity(intent1);
                }else if(selection.equals("Clothing")){
                    Intent intent1 = new Intent(getApplicationContext(),ClothingActivity.class);
                    startActivity(intent1);
                }else if(selection.equals("Logout")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingActivity.this);
                    // 2. Set the basic information for the builder object
                    builder.setTitle("Exit Shopping");
                    builder.setMessage("Do you want to Exit");
                    builder.setIcon(R.drawable.alerticon);
                    // 3. Implement the listener for the buttons you specified on Alert dialog
                    AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener(){
                        // Write you logic for button click actions based on which buttin is clicked from the alert dialog
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==dialog.BUTTON_POSITIVE){
                                dialog.dismiss(); // dismiss the dialog
                                finish(); // to destroy the activity
                            }
                            else if(which==dialog.BUTTON_NEGATIVE){
                                dialog.dismiss(); // dismiss the dialog, but activity is still alive
                            }
                        }
                    };
                    // 4. Here we are going to set two buttons on the Alert dialog with listener for click event
                    builder.setPositiveButton("Yes",listener);
                    builder.setNegativeButton("No",listener);
                    // 5. need to show the dialog
                    builder.show();

                }else {
                    Toast.makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };
        aBar.addTab(aBar.newTab().setText("Shopping").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Electronics").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Clothing").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Beauty").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Food").setTabListener(listener));
        aBar.addTab(aBar.newTab().setText("Logout").setTabListener(listener));

    }


    public void showm1(View view){
        Context context = getApplicationContext(); // or you can pass directly this keyword.
        CharSequence text = "You have chosen the Electronics category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration); toast.show();
    }

    public void showm2(View view){
        Context context = getApplicationContext(); // or you can pass directly this keyword.
        CharSequence text = "You have chosen the Clothing category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration); toast.show();
    }

    public void showm3(View view){
        Context context = getApplicationContext(); // or you can pass directly this keyword.
        CharSequence text = "You have chosen the Beauty category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration); toast.show();
    }

    public void showm4(View view){
        Context context = getApplicationContext(); // or you can pass directly this keyword.
        CharSequence text = "You have chosen the Food category of shopping";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration); toast.show();
    }

    public void BeautyClic(View view){
        Intent intent = new Intent(this,BeautyActivity.class);
        startActivity(intent);
    }

    public void ClothingClic(View view){
        Intent intent = new Intent(this,ClothingActivity.class);
        startActivity(intent);
    }

    public void FoodClic(View view){
        Intent intent = new Intent(this,FoodActivity.class);
        startActivity(intent);
    }

    public void ElectronicClic(View view){
        Intent intent = new Intent(this,ElectronicActivity.class);
        startActivity(intent);
    }

}
