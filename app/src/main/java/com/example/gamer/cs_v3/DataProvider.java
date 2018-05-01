package com.example.gamer.cs_v3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gamer.cs_v3.Model.Game;
import com.example.gamer.cs_v3.Model.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataProvider  {
    Context context;
    LinearLayout container = Activity_Main.content;


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

                                TextView t = new TextView(context);
                                t.setText(g.getName());
                                t.setOnClickListener(e -> textClick(g.getId()));

                                formatTextview(t, "category");
                            }

                            // matches
                            if(type == 1) {
                                JSONObject league = response.getJSONObject(i).getJSONObject("league");
                                JSONObject tournament = response.getJSONObject(i).getJSONObject("tournament");
                                JSONArray teams = response.getJSONObject(i).getJSONArray("opponents");

                                Match match = new Match();
                                for (int j = 0; j < teams.length(); j++) {
                                    JSONObject team = teams.getJSONObject(j).getJSONObject("opponent");

                                    if(j == 0) {
                                        match.setTeam1(team.getString("name"));
                                    } else {
                                        match.setTeam2(team.getString("name"));
                                    }

                                }
                                match.setId(jsonObject.getInt("id"));
                                match.setName(jsonObject.getString("name"));
                                match.setTime(jsonObject.getString("begin_at"));
                                match.setTournament(tournament.getString("name"));
                                match.setLeague(league.getString("name"));


                                TextView headText = new TextView(context);
                                headText.setText(match.getName());
                                TextView subtext = new TextView(context);
                                subtext.setText(match.infoToString());

                                formatTextview(headText, "head");
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
        container.removeAllViews();

        if(id == 14) {
            getResponse("https://api.pandascore.co/ow/matches/upcoming?page=1&token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34", 1);
        } else if (id == 1) {
            getResponse("https://api.pandascore.co/lol/matches/upcoming?page=1&token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34", 1);
        }else if(id == 4) {
            getResponse("https://api.pandascore.co/dota2/matches/upcoming?page=1&token=PUbBYoQNl8UBcjZ0nvOHSPbJEGMEHtV75-437VksZ2bsKdNOb34", 1);
        }else {
            Toast.makeText(context, "Not available", Toast.LENGTH_LONG).show();
        }
    }

    private void formatTextview(TextView textView, String type) {

        if(type.equalsIgnoreCase("category")) {
            textView.setTextSize(30);
            textView.setWidth(container.getWidth());
            //textView.setHeight(container.getHeight() / 4);

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

        container.addView(textView);
    }
}
