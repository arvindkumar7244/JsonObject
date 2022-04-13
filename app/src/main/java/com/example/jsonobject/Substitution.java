package com.example.jsonobject;

public class Substitution {
   private final int time;
   private final String substitution;
   private final String team;
    public int getTime() {
        return time;
    }

    public String getSubstitution() {
        return substitution;
    }

    public Substitution(int time, String substitution, String team) {
        this.time = time;
        this.substitution = substitution;
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
