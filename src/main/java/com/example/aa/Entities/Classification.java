package com.example.aa.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Classification {

    public Map<Integer, Team> getClassification(List<Team> teamList) {
        teamList.sort((team1, team2) -> {
            int pointsComparison = Integer.compare(team2.getPoints(), team1.getPoints());
            if (pointsComparison == 0) {
                return Integer.compare(team2.getGoalDifference(), team1.getGoalDifference());
            }
            return pointsComparison;
        });

        Map<Integer, Team> classification = new LinkedHashMap<>();
        int rank = 1;
        for (Team team : teamList) {
            classification.put(rank++, team);
        }

        for (Map.Entry<Integer, Team> entry : classification.entrySet()) {
            System.out.println("Rank: " + entry.getKey() + ", Team: " + entry.getValue().getName() + ", Points: " + entry.getValue().getPoints() + ", Saldo de Gols: " + entry.getValue().getGoalDifference());
        }

        return classification;
    }
}
