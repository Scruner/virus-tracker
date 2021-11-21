package ru.vdv.virustracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vdv.virustracker.models.Location;
import ru.vdv.virustracker.service.VirusDataService;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  VirusDataService virusDataService;

  @GetMapping("/")
  public String home(Model model) {
    List<Location> allStats = virusDataService.getAllStats();
    int totalCasesWorldwide = allStats.stream().mapToInt(Location::getLatestTotalCases).sum();
    int totalNewCases = allStats.stream().mapToInt(Location::getDelta).sum();
    model.addAttribute("locationStatistics", allStats);
    model.addAttribute("totalCasesWorldwide", totalCasesWorldwide);
    model.addAttribute("totalNewCases", totalNewCases);
    return "home";
  }
}
