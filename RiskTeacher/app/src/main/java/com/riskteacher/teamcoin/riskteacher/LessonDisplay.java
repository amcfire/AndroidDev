package com.riskteacher.teamcoin.riskteacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class LessonDisplay extends AppCompatActivity {

    WebView webview;
    TextView title_textview;

    int lesson_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_display);

        Intent o = getIntent();
        lesson_number = o.getIntExtra("lesson", 0);

        title_textview = findViewById(R.id.title);
        webview = findViewById(R.id.web_view);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String url;
        String title;
        switch (lesson_number){
            case R.id.lesson1:
                title = "Lesson 1 - Intro";
                url = "file:///android_asset/Lesson1.html";
                break;

            case R.id.lesson2:
                title = "Lesson 2: PaperMoney Simulator";
                url = "file:///android_asset/Lesson2.html";
                break;

            case R.id.lesson3:
                title = "Lesson 3: Operation Types";
                url = "file:///android_asset/Lesson3.html";
                break;

            case R.id.lesson4:
                title = "Lesson 4: About Strategies ";
                url = "file:///android_asset/Lesson4.html";
                break;

            case R.id.lesson5:
                title = "Lesson 5: Strategies, enter and leave";
                url = "file:///android_asset/Lesson5.html";
                break;

            case R.id.lesson6:
                title = "Lesson 6: Strategies – Operation Size ";
                url = "file:///android_asset/Lesson6.html";
                break;

            case R.id.lesson7:
                title = "Lesson 7: Strategies – What to do when lose";
                url = "file:///android_asset/Lesson7.html";
                break;

            case R.id.lesson8:
                title = "Lesson 8: Strategies – What to do when win";
                url = "file:///android_asset/Lesson8.html";
                break;

            case R.id.lesson9:
                title = "Lesson 9: Maximum admissible risk";
                url = "file:///android_asset/Lesson9.html";
                break;

            case R.id.lesson10:
                title = "Lesson 10: Automatic Strategies - Robots";
                url = "file:///android_asset/Lesson10.html";
                break;

            case R.id.lesson11:
                title = "Lesson 11: Robot Settings";
                url = "file:///android_asset/Lesson11.html";
                break;

                default:
                    title = "";
                    url = "";
                    break;
        }
        title_textview.setText(title);
        webview.loadUrl(url);
    }
}
