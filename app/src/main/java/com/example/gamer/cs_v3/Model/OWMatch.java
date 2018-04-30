package com.example.gamer.cs_v3.Model;

public class OWMatch implements Match {
    public int id;
    public String name;
    public String time;
    public String league;
    public String tournament;

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String infoToString () {
        return "League: " + league + " \nTournament: " + tournament +  "\n" +
                "Time: " + time;
    }
}
