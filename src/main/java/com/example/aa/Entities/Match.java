package com.example.aa.Entities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Match {
    private Date dataPartida;
    private Integer rodada;
    private Team timeMandante;
    private Team timeVisitante;
    private Integer golMandante;
    private Integer golVisitante;

    public Match(Date dataPartida, Integer rodada, Team timeMandante, Team timeVisitante, Integer golMandante, Integer golVisitante) {
        this.dataPartida = dataPartida;
        this.rodada = rodada;
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.golMandante = golMandante;
        this.golVisitante = golVisitante;
    }

    public Team vencedor() {
        if (golMandante > golVisitante) {
            return timeMandante;
        } else if (golMandante < golVisitante) {
            return timeVisitante;
        } else {
            return null;
        }
    }

    public Team perdedor() {
        if (golMandante < golVisitante) {
            return timeMandante;
        } else if (golMandante > golVisitante) {
            return timeVisitante;
        } else {
            return null;
        }
    }

    public Integer getSaldoGolsVencedor() {
        Team vencedor = vencedor();

        if (vencedor == null) {
            return 0;
        }

        if (vencedor.equals(timeMandante)) {
            return golMandante - golVisitante;
        } else if (vencedor.equals(timeVisitante)) {
            return golVisitante - golMandante;
        }

        return 0;
    }

    public Integer getSaldoGolsPerdedor() {
        Team perdedor = perdedor();

        if (perdedor == null) {
            return 0;
        }

        if (perdedor.equals(timeMandante)) {
            return golMandante - golVisitante;
        } else if (perdedor.equals(timeVisitante)) {
            return golVisitante - golMandante;
        }

        return 0;
    }

//    public static List<Match> getAllMatches() {
//        List<Match> matchList = new ArrayList<>();
//        String csvFile = "src/main/resources/brasileirao_2022.csv";
//
//        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
//            String[] nextLine;
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            reader.readNext();
//
//            while ((nextLine = reader.readNext()) != null) {
//                Date dataPartida = dateFormat.parse(nextLine[1]);
//                Integer rodada = Integer.parseInt(nextLine[2]);
//                Team timeMandante = new Team(nextLine[7]);
//                Team timeVisitante = new Team(nextLine[8]);
//                Integer golMandante = Integer.parseInt(nextLine[17]);
//                Integer golVisitante = Integer.parseInt(nextLine[18]);
//
//                Match match = new Match(dataPartida, rodada, timeMandante, timeVisitante, golMandante, golVisitante);
//                matchList.add(match);
//            }
//        } catch (IOException | CsvValidationException | ParseException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//        return matchList;
//    }
}