package com.example.amcfire.favoritewebsites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<WebUser> wusers = new ArrayList<WebUser>();
    public WebUser currenUser;
    public boolean validUser=false;
    WebView wview;
    TextView tv;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        wusers.add(new WebUser("guest","guest"));
        wusers.add(new WebUser("admin","admin"));
        wview = (WebView) findViewById(R.id.wview1);
        wview.getSettings().setJavaScriptEnabled(true);
        wview.getSettings().setDomStorageEnabled(true);
        wview.getSettings().setBuiltInZoomControls(true);
        wview.addJavascriptInterface(this,"myinterface"); // Once you type this line you will get the error, by adding @JavascriptInterface before the method which you interact through HTML and Android activity
        wview.loadUrl("file:///android_asset/login.html");
        // Integrate the browser with WebView
        /* We are just displaying Toast message in below three methods,
        but in real time you can write your pre, post execution operations in your logic
        by overriding WebViewClient methods*/
        wview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(getApplicationContext(), " Page loading is started",Toast.LENGTH_LONG).show();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
                //return super.shouldOverrideUrlLoading(view, url);
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(),"On Page finished",Toast.LENGTH_LONG).show();
            }
        });
    }

    @JavascriptInterface
    public String validate(String name,String pass){
        currenUser = new WebUser(name, pass);
        Toast toast;
        Context context;
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        String output;

        if(validateUser(currenUser)){
            //sp part
            SharedPreferences spf = getSharedPreferences("webFavoritesLab", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = spf.edit();
            spe.putString("user",currenUser.getWuser());
            spe.putString("pass",currenUser.getWpass());
            spe.commit();
            //login succesfull
            validUser = true;
            output = "valid user";
            context = getApplicationContext(); // or you can pass directly this keyword.
            text = "Login successful";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            WebView vwiew1=(WebView) findViewById(R.id.wview1);
            vwiew1.getSettings().setJavaScriptEnabled(true);
            vwiew1.getSettings().setDomStorageEnabled(true);
            vwiew1.getSettings().setBuiltInZoomControls(true);
            vwiew1.loadUrl("file:///android_asset/favorites.html");
            vwiew1.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    Toast.makeText(getApplicationContext(), " Page loading is started",Toast.LENGTH_LONG).show();
                }
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
                    //return super.shouldOverrideUrlLoading(view, url);
                    return false;
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    Toast.makeText(getApplicationContext(),"On Page finished",Toast.LENGTH_LONG).show();
                }
            });
        }else{
            validUser = false;
            output = "not valid";
            context = getApplicationContext(); // or you can pass directly this keyword.
            text = "Invalid Credentials, please try again";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        };
        return output;
    }


    public boolean validateUser(WebUser wu){
        boolean result = false;
        for (WebUser w2:wusers) {
            result = result||w2.verifyUser(wu);
        }
        return result;
    }

    @JavascriptInterface
    public void fGoto(String passlink){
        Context context = getApplicationContext(); // or you can pass directly this keyword.
        WebView vwiew1=(WebView) findViewById(R.id.wview1);
        vwiew1.getSettings().setJavaScriptEnabled(true);
        vwiew1.getSettings().setDomStorageEnabled(true);
        vwiew1.getSettings().setBuiltInZoomControls(true);
        Toast.makeText(getApplicationContext(), "passlink: "+passlink,Toast.LENGTH_LONG).show();
        vwiew1.loadUrl(passlink);
        vwiew1.loadUrl( "javascript:window.location.reload( true )" );
        vwiew1.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(getApplicationContext(), " Page loading is started",Toast.LENGTH_LONG).show();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
                //return super.shouldOverrideUrlLoading(view, url);
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(),"On Page finished",Toast.LENGTH_LONG).show();
            }
        });
    }

}
