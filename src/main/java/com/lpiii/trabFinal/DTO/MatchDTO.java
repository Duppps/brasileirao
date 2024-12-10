package com.lpiii.trabFinal.DTO;

import java.sql.Timestamp;

public record MatchDTO(
        Long homeTeamId,
        Long visitingTeamId,
        int homeGoals,
        int visitingGoals,
        Timestamp dateTime
) {}
