package com.example.gamer.cs_v3;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gamer.cs_v3.Model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity {
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);

        // UI
        content = findViewById(R.id.scroll_content);

        // Setup
        final TextView textView = new TextView(this);
        textView.setText("Test");
        content.addView(textView);


        String url = "https://api.pandascore.co/videogames?token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34";

        DataProvider data = new DataProvider(this, url);
        ArrayList<Game> games = data.getGames();

        for (int i = 0; i < games.size(); i++) {
            TextView txt = new TextView(this);
            txt.setText(data.getGames().get(i).getName());
            content.addView(txt);
            Log.e("RESPONSE", data.getGames().toString());
        }


        /*
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Do something with response
                    //mTextView.setText(response.toString());

                    // Process the JSON
                    try{
                        // Loop through the array elements
                        for(int i=0;i<response.length();i++){
                            // Get current json object
                            JSONObject game = response.getJSONObject(i);

                            // Get the current student (json object) data
                            int id = game.getInt("id");
                            String name = game.getString("name");
                            textView.append(id + " " + name + "\n");
                            // Display the formatted json data in text view

                        }

                        textView.append(" " + response.length());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Do something when error occurred
                    Log.e("Response", "Error");
                }
        );


        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
        */
    }
}
