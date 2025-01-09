package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.DTO.MatchDTO;
import com.lpiii.trabFinal.Entities.Match;
import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.MatchRepository;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import com.lpiii.trabFinal.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private MatchService matchService;

    @GetMapping("")
    public String getTeams(Model model) {
        List<Match> matchList = matchRepository.findAll();
        List<Team> teamList = teamRepository.findAll();

        model.addAttribute("teamList", teamList);
        model.addAttribute("matchList", matchList);

        return "pages/match";
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMatch(@PathVariable Long id, @RequestBody MatchDTO matchDTO) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match not found with id " + id));

        Team homeTeam = match.getHomeTeam();
        Team visitingTeam = match.getVisitingTeam();

        if (match.getGoalsHomeTeam() > match.getGoalsVisitingTeam()) {
            homeTeam.setPoints(homeTeam.getPoints() - 3);
            homeTeam.setWins(homeTeam.getWins() - 1);
            visitingTeam.setLosses(visitingTeam.getLosses() - 1);
        } else if (match.getGoalsHomeTeam() < match.getGoalsVisitingTeam()) {
            visitingTeam.setPoints(visitingTeam.getPoints() - 3);
            visitingTeam.setWins(visitingTeam.getWins() - 1);
            homeTeam.setLosses(homeTeam.getLosses() - 1);
        } else {
            homeTeam.setPoints(homeTeam.getPoints() - 1);
            visitingTeam.setPoints(visitingTeam.getPoints() - 1);
            homeTeam.setDraws(homeTeam.getDraws() - 1);
            visitingTeam.setDraws(visitingTeam.getDraws() - 1);
        }

        homeTeam.setGoalsScored(homeTeam.getGoalsScored() - match.getGoalsHomeTeam());
        homeTeam.setGoalsConceded(homeTeam.getGoalsConceded() - match.getGoalsVisitingTeam());
        visitingTeam.setGoalsScored(visitingTeam.getGoalsScored() - match.getGoalsVisitingTeam());
        visitingTeam.setGoalsConceded(visitingTeam.getGoalsConceded() - match.getGoalsHomeTeam());

        Optional<Team> optionalHomeTeam = teamRepository.findById(matchDTO.homeTeamId());
        Optional<Team> optionalVisitingTeam = teamRepository.findById(matchDTO.visitingTeamId());

        if (optionalHomeTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Home team not found");
        }
        if (optionalVisitingTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visiting team not found");
        }

        Team updatedHomeTeam = optionalHomeTeam.get();
        Team updatedVisitingTeam = optionalVisitingTeam.get();

        int goalsHomeTeam = matchDTO.homeGoals();
        int goalsVisitingTeam = matchDTO.visitingGoals();
        Timestamp dateTime = matchDTO.dateTime();

        match.setHomeTeam(updatedHomeTeam);
        match.setVisitingTeam(updatedVisitingTeam);
        match.setGoalsHomeTeam(goalsHomeTeam);
        match.setGoalsVisitingTeam(goalsVisitingTeam);
        match.setDateTime(dateTime);

        if (goalsHomeTeam > goalsVisitingTeam) {
            updatedHomeTeam.addPoints(3);
            updatedHomeTeam.addWin();
            updatedVisitingTeam.addLoss();
        } else if (goalsHomeTeam < goalsVisitingTeam) {
            updatedVisitingTeam.addPoints(3);
            updatedVisitingTeam.addWin();
            updatedHomeTeam.addLoss();
        } else {
            updatedHomeTeam.addPoints(1);
            updatedVisitingTeam.addPoints(1);
            updatedHomeTeam.addDraw();
            updatedVisitingTeam.addDraw();
        }

        updatedHomeTeam.addGoalsScored(goalsHomeTeam);
        updatedHomeTeam.addGoalsConceded(goalsVisitingTeam);
        updatedVisitingTeam.addGoalsScored(goalsVisitingTeam);
        updatedVisitingTeam.addGoalsConceded(goalsHomeTeam);

        matchRepository.save(match);
        teamRepository.save(updatedHomeTeam);
        teamRepository.save(updatedVisitingTeam);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/")
    public ResponseEntity<String> createMatch(@RequestBody MatchDTO matchDTO) {
        try {
            Boolean result = matchService.createMatch(matchDTO);

            if (!result) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating match");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating match: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match not found with id " + id));

        Team homeTeam = match.getHomeTeam();
        Team visitingTeam = match.getVisitingTeam();

        if (match.getGoalsHomeTeam() > match.getGoalsVisitingTeam()) {
            homeTeam.setPoints(homeTeam.getPoints() - 3);
            homeTeam.setWins(homeTeam.getWins() - 1);
            visitingTeam.setLosses(visitingTeam.getLosses() - 1);
        } else if (match.getGoalsHomeTeam() < match.getGoalsVisitingTeam()) {
            visitingTeam.setPoints(visitingTeam.getPoints() - 3);
            visitingTeam.setWins(visitingTeam.getWins() - 1);
            homeTeam.setLosses(homeTeam.getLosses() - 1);
        } else {
            homeTeam.setPoints(homeTeam.getPoints() - 1);
            visitingTeam.setPoints(visitingTeam.getPoints() - 1);
            homeTeam.setDraws(homeTeam.getDraws() - 1);
            visitingTeam.setDraws(visitingTeam.getDraws() - 1);
        }

        homeTeam.setGoalsScored(homeTeam.getGoalsScored() - match.getGoalsHomeTeam());
        homeTeam.setGoalsConceded(homeTeam.getGoalsConceded() - match.getGoalsVisitingTeam());
        visitingTeam.setGoalsScored(visitingTeam.getGoalsScored() - match.getGoalsVisitingTeam());
        visitingTeam.setGoalsConceded(visitingTeam.getGoalsConceded() - match.getGoalsHomeTeam());

        teamRepository.save(homeTeam);
        teamRepository.save(visitingTeam);

        matchRepository.delete(match);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
