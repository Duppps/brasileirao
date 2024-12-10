package com.lpiii.trabFinal.Services;

import com.lpiii.trabFinal.DTO.MatchDTO;
import com.lpiii.trabFinal.Entities.Match;
import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.MatchRepository;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    public Boolean createMatch(MatchDTO matchDTO) {
        Long idHomeTeam = matchDTO.homeTeamId();
        Long idVisitingTeam = matchDTO.visitingTeamId();
        int goalsHomeTeam = matchDTO.homeGoals();
        int goalsVisitingTeam = matchDTO.visitingGoals();
        Timestamp dateTime = matchDTO.dateTime();

        Optional<Team> optionalHomeTeam = teamRepository.findById(idHomeTeam);
        Optional<Team> optionalVisitingTeam = teamRepository.findById(idVisitingTeam);

        if (optionalHomeTeam.isEmpty() || optionalVisitingTeam.isEmpty()) {
            return false;
        }

        Team homeTeam = optionalHomeTeam.get();
        Team visitingTeam = optionalVisitingTeam.get();

        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setVisitingTeam(visitingTeam);
        match.setGoalsHomeTeam(goalsHomeTeam);
        match.setGoalsVisitingTeam(goalsVisitingTeam);
        match.setDateTime(dateTime);

        matchRepository.save(match);

        return true;
    }
}
