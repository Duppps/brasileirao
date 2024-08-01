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
    private Integer points = 0;
    private Integer saldoGols = 0;

    public Team(String name) {
        this.name = name;
    }

    public void setPoints(Integer points) {
        this.points = points + this.points;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static Boolean verifyIfTeamExists(List<Team> teamList, Team team) {
        boolean teamExists = false;
        for (Team t : teamList) {
            if (t.getName().equals(team.getName())) {
                teamExists = true;
                break;
            }
        }

        return teamExists;
    }

    public static List<Team> generateAllTeams() {
        String csvFile = "src/main/resources/brasileirao_2022.csv";
        List<Team> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Team team = new Team(nextLine[7]);
                if (!list.contains(team)) {
                    list.add(team);
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return list;
    }
}