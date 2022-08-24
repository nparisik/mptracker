package com.np.monkeypoxtracker.controller;

import com.np.monkeypoxtracker.model.LocationStats;
import com.np.monkeypoxtracker.service.MonkeypoxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MonkeypoxDataService monkeypoxDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = monkeypoxDataService.getAllStats();
        Integer totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        Integer totalDeaths = allStats.stream().mapToInt(stat -> stat.getDeaths()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalDeaths", totalDeaths);

        return "home";
    }
}
