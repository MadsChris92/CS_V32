package com.example.gamer.cs_v3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gamer.cs_v3.Model.Game;
import com.example.gamer.cs_v3.Model.OWMatch;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataProvider  {
    Context context;


    public DataProvider(Context context){
        this.context = context;
        //this.url = url;
    }

    public void getResponse(String url, int type) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            // games
                            if(type == 0){
                                Game g = new Game();
                                g.setId(jsonObject.getInt("id"));
                                g.setName(jsonObject.getString("name"));

                                LinearLayout linearLayout = new LinearLayout(context);
                                TextView t = new TextView(context);
                                t.setText(g.getName());
                                t.setOnClickListener(e -> textClick(g.getId()));

                                formatTextview(t, "head");
                                //Activity_Main.content.addView(t);
                            }

                            // overwatch matches
                            if(type == 1) {
                                JSONObject league = response.getJSONObject(i).getJSONObject("league");
                                JSONObject tournament = response.getJSONObject(i).getJSONObject("tournament");

                                OWMatch match = new OWMatch();
                                match.setId(jsonObject.getInt("id"));
                                match.setName(jsonObject.getString("name"));
                                match.setTime(jsonObject.getString("begin_at"));
                                match.setTournament(tournament.getString("name"));
                                match.setLeague(league.getString("name"));

                                LinearLayout linearLayout = new LinearLayout(context);
                                TextView textView = new TextView(context);
                                textView.setText(match.getName());
                                TextView subtext = new TextView(context);
                                subtext.setText(match.infoToString());

                                formatTextview(textView, "head");
                                formatTextview(subtext, "sub");
                            }
                            Log.e("Object", jsonObject.toString());
                        }
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
        Activity_Main.content.removeAllViews();

        if(id == 14) {
            getResponse("https://api.pandascore.co/ow/matches/upcoming?page=1&token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34", 1);
        } else if (id == 1) {
            getResponse("https://api.pandascore.co/lol/matches/upcoming?page=1&token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34", 1);
        }else {
            Toast.makeText(context, "Not available", Toast.LENGTH_LONG).show();
        }
    }

    private void formatTextview(TextView textView, String type) {

        if(type.equalsIgnoreCase("category")) {
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(0,0,0,50);
        }

        if(type.equalsIgnoreCase("head")) {
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
        }

        if(type.equalsIgnoreCase("sub")) {
            textView.setTextSize(12);
            textView.setTextColor(Color.GRAY);
            textView.setPadding(0,0,0,50);
        }

        Activity_Main.content.addView(textView);
    }
}
