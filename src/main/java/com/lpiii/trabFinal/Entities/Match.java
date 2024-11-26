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

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team visitingTeam;

    @Column
    private int goalsHomeTeam;

    @Column
    private int goalsVisitingTeam;

    @Column
    private Timestamp dateTime;
}
