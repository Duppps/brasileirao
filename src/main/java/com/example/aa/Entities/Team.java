package com.example.aa.Entities;

public class Team {
    private String name;
    private String city;
    private String stadium;
    private int points;

    public Team(String name, String city, String stadium) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
        this.points = 0;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String getStadium() {
        return this.stadium;
    }
}
