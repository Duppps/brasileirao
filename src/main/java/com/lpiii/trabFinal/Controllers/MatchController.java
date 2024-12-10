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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/matchs")
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
}
