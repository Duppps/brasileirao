package com.example.aa.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private String name;
    private String stadium;
    private String city;
    private Integer points = 0;
    private Integer goalDifference = 0;
    private Integer wins = 0;
    private Integer losses = 0;
    private Integer draws = 0;
    private Integer goalsScored = 0;
    private Integer goalsConceded = 0;
    private Integer performance = 0;

    public Team(String name, String stadium, String city) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
    }

    public void setPoints(Integer points) {
        this.points += points;
    }
    public void setGoalsDifference(Integer points) {
        this.goalDifference += points;
    }

    public String toString() {
        return this.name;
    }
}