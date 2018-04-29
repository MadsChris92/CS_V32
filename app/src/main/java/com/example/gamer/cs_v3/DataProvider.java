package com.example.gamer.cs_v3;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gamer.cs_v3.Model.Game;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataProvider  {
    Context context;
    String url;

    public DataProvider(Context context, String url){
        this.context = context;
        this.url = url;
    }

    public void showGames() {
        //String url = "https://api.pandascore.co/videogames?token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34";
        ArrayList<Game> games = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject game = response.getJSONObject(i);

                            Game g = new Game();
                            g.setId(game.getInt("id"));
                            g.setName(game.getString("name"));
                            games.add(g);

                            TextView t = new TextView(context);


                            t.setTextSize(20);
                            t.setText("ID: "+ g.getId() + " Name:" + g.getName());
                            t.setOnClickListener(e -> textClick(g.getId()));

                            Activity_Main.content.addView(t);
                        }
                        Log.e("Array",games.size() + " ");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.e("Volley", "Response error");
                }
        );

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    private void textClick(int id) {
        Toast.makeText(context, id + "", Toast.LENGTH_LONG).show();
    }


}
