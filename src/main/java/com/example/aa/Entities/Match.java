package com.example.aa.Entities;


import java.util.Date;

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
}
