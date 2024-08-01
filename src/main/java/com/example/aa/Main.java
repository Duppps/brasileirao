package com.example.aa;

import com.example.aa.Entities.Classification;
import com.example.aa.Entities.Match;
import com.example.aa.Entities.Team;
import com.example.aa.GUI.MainGUI;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Team> allTeams = new ArrayList<>();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI(allTeams);
            }
        });

//        List<Match> matchList = Match.getAllMatches();
//
//
//        for (Match match : matchList) {
//            Team vencedor = match.vencedor();
//
//            for (Team team : allTeams) {
//                if (vencedor != null) {
//                    if (team.getName().equals(vencedor.getName())) {
//                        team.setPoints(3);
//                        team.setSaldoGols(match.getSaldoGolsVencedor());
//                    } else if (team.getName().equals(match.perdedor().getName())) {
//                        team.setSaldoGols(match.getSaldoGolsPerdedor());
//                    }
//                } else {
//                    if (team.getName().equals(match.getTimeMandante().getName())) {
//                        team.setPoints(1);
//                    }
//                    if (team.getName().equals(match.getTimeVisitante().getName())) {
//                        team.setPoints(1);
//                    }
//                }
//            }
//        }
//
//        Classification classification = new Classification();
//        Map<Integer, Team> rankedTeams = classification.getClassification(allTeams);
//
//        rankedTeams.forEach((position, team) -> {
//            //System.out.println("Position: " + position + ", Team: " + team.getName() + ", Points: " + team.getPoints());
//        });
    }
}
