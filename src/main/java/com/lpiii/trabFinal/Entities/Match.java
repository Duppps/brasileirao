package com.lpiii.trabFinal.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeTeamId", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitingTeamId", nullable = false)
    private Team visitingTeam;

    @Column(nullable = false)
    private int goalsHomeTeam;

    @Column(nullable = false)
    private int goalsVisitingTeam;

    @Column(nullable = false)
    private Timestamp dateTime;
}
