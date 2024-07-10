package com.example.aa.Entities;

import java.time.LocalTime;

public class Match {
    private Team homeTeam;
    private Team visitingTeam;
    private LocalTime date;
    private int homeTeamGoal;
    private int visitingTeamGoal;
    private String result;
    private int status;

    public Match(Team homeTeam, Team visitingTeam, LocalTime date) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.status = 1;
    }

    public void setHomeTeamGoal() {
        this.homeTeamGoal += 1;
    }

    public void setVisitingTeamGoal() {
        this.visitingTeamGoal += 1;
    }

    public void setStatus(int status) {
        if (status == 0) {
            if (homeTeamGoal > visitingTeamGoal) homeTeamGoal.setPoints(3);
        }
    }

    public int getStatus() {
        return this.status;
    }

    public int getHomeTeamGoal() {
        return this.homeTeamGoal;
    }

    public int getVisitingTeamGoal() {
        return this.visitingTeamGoal;
    }

    public String getResult() {
        return this.result;
    }

    public Team getHomeTeam() {
        return this.homeTeam;
    }

    public Team getVisitingTeam() {
        return this.visitingTeam;
    }

    public LocalTime getDate() {
        return this.date;
    }
}
