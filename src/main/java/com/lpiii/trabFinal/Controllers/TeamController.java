package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.Entities.Match;
import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.MatchRepository;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    @GetMapping("")
    public String getTeams(Model model) {
        List<Team> teamList = teamRepository.findAll();
        model.addAttribute("teamList", teamList);
        return "pages/team";
    }

    @GetMapping("/{id}")
    public String getTeam(@PathVariable Long id, Model model) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty()) {
            return "pages/error/404";
        }

        Team team = optionalTeam.get();
        List<Match> matches = matchRepository.findByTeam(team);

        model.addAttribute("team", team);
        model.addAttribute("matchList", matches);

        return "pages/detail";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody Team teamUpdated) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Team not found with id " + id));

        team.setName(teamUpdated.getName());
        team.setCity(teamUpdated.getCity());
        team.setStadium(teamUpdated.getStadium());

        teamRepository.save(team);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Team not found with id " + id));

        teamRepository.delete(team);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
