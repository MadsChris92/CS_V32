package com.example.gamer.cs_v3;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gamer.cs_v3.Model.Game;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataProvider  {
    Context context;
    String url;
    public ArrayList<Game> ggames = new ArrayList<>();

    public DataProvider(Context context, String url){
        this.context = context;
        this.url = url;
        getGames();
    }

    public ArrayList<Game> getGames() {
        //String url = "https://api.pandascore.co/videogames?token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34";
        ArrayList<Game> games = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Do something with response
                    //mTextView.setText(response.toString());
                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject game = response.getJSONObject(i);
                            // Get the current student (json object) data
                            //int id = game.getInt("id");
                            //String name = game.getString("name");
                            Game g = new Game();
                            g.setId(game.getInt("id"));
                            g.setName(game.getString("name"));
                            games.add(g);
                            ggames.add(g);
                            // textView.append(id + " " + name + "\n");
                            // Display the formatted json data in text view

                        }
                        Log.e("Array",games.size() + " ");

                        //textView.append(" " + response.length());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Do something when error occurred
                    Log.e("Response", "Error");
                }
        );

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

        return games;
    }
}
