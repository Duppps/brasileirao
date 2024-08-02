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
        this.stadium = stadium;
        this.city = city;
    }

    public void updateGoals(Integer scored, Integer conceded) {
        this.goalsScored += scored;
        this.goalsConceded += conceded;
        this.goalDifference = this.goalsScored - this.goalsConceded;

        if (scored > conceded) {
            this.wins++;
            this.points += 3;
        } else if (scored < conceded) {
            this.losses++;
        } else {
            this.draws++;
            this.points += 1;
        }
    }

    public double getPerformance() {
        int totalMatches = this.wins + this.losses + this.draws;
        int totalPointsPossible = totalMatches * 3;

        if (totalPointsPossible == 0) {
            return 0;
        }

        return (int) Math.round(((double) this.points / totalPointsPossible) * 100);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
