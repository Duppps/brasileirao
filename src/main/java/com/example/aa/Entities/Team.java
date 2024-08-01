package com.example.aa.Entities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Team {
    private String name;
    private String stadium;
    private String city;
    private Integer points = 0;
    private Integer saldoGols = 0;
    private Integer wins = 0;
    private Integer defeats = 0;
    private Integer draws = 0;
    private Integer goalsScored = 0;
    private Integer goalsConceded = 0;
    private Integer utilization = 0;

    public Team(String name, String stadium, String city) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
    }

    public void setPoints(Integer points) {
        this.points += points;
    }
    public void setSaldoGols(Integer points) {
        this.saldoGols += points;
    }

    public String toString() {
        return this.name;
    }
}