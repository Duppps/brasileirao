package com.lpiii.trabFinal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TeamController {
    @GetMapping("/team")
    public String teamPage(Model model) {
        return "pages/team";
    }
}
