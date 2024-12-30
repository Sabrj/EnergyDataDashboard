package com.example.EnergyDataDashboard.controller;

import com.example.EnergyDataDashboard.classes.EnergyData;
import com.example.EnergyDataDashboard.service.EnergyDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final EnergyDataService energyDataService;

    public HomeController(EnergyDataService energyDataService) {
        this.energyDataService = energyDataService;
    }

    @GetMapping("/energy-data")
    public List<EnergyData> getEnergyData() {
        List<EnergyData> data = energyDataService.getEnergyData();
        if (data == null || data.isEmpty()) {
            return List.of((EnergyData) List.of("No data available"));
        }
        return data;
    }
}

