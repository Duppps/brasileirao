package com.lpiii.trabFinal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassificationController {
    @GetMapping("/")
    public String homePage(Model model) {
        return "pages/home";
    }
}
