package com.example.gamer.cs_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity_Main extends AppCompatActivity {
    public static LinearLayout content;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);

        // UI
        content = findViewById(R.id.content);
        btnHome = findViewById(R.id.button_home);

        // Setup
        final TextView textView = new TextView(this);
        content.addView(textView);

        String homeURL = "https://api.pandascore.co/videogames?token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34";
        DataProvider data = new DataProvider(this);
        data.getResponse(homeURL, 0);

        // Listeners
        btnHome.setOnClickListener(e -> {
            data.getResponse(homeURL, 0);
            content.removeAllViews();
        });


    }
}
