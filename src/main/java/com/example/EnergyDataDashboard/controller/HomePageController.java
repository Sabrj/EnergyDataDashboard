package com.example.EnergyDataDashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Für die Weiterleitung zur HTML-Seite
@RequestMapping("/")
public class HomePageController {

    // Weiterleitung zur dashboard.html-Seite
    @GetMapping
    public String home() {
        return "redirect:/dashboard.html";  // Umleitung zur dashboard.html
    }
}
