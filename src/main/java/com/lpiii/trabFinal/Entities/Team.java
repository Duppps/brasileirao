package com.lpiii.trabFinal.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String stadium;

    @Column(nullable = false)
    private int points = 0;

    @Column(nullable = false)
    private int wins = 0;

    @Column(nullable = false)
    private int draws = 0;

    @Column(nullable = false)
    private int losses = 0;

    @Column(nullable = false)
    private int goalsScored = 0;

    @Column(nullable = false)
    private int goalsConceded = 0;

    @Column(nullable = false)
    private int matches;

    @Override
    public String toString() {
        return this.name;
    }

    public void addMatches() {
        this.matches++;
    }

    public void addWin() {
        this.wins++;
    }

    public void addDraw() {
        this.draws++;
    }

    public void addLoss() {
        this.losses++;
    }

    public void addGoalsScored(int goals){
        this.goalsScored += goals;
    }

    public void addGoalsConceded(int goals) {
        this.goalsConceded += goals;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
