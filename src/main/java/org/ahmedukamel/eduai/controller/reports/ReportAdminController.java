package org.ahmedukamel.eduai.controller.reports;

import org.ahmedukamel.eduai.model.Report;
import org.ahmedukamel.eduai.model.Suggestion;
import org.ahmedukamel.eduai.service.reports.ReportService;
import org.ahmedukamel.eduai.service.reports.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ReportAdmin")
public class ReportAdminController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/reports-and-suggestions")
    public String getReports(Model model) {
        model.addAttribute("reports", reportService.getAllReports());
        model.addAttribute("suggestions", suggestionService.getAllSuggestions());
        return "api/v1/adminreports";
    }

    @PutMapping("/reports/{id}")
    @ResponseBody
    public Report updateReport(@PathVariable int id, @RequestBody Report report) {
        return reportService.updateReport(id, report);
    }

    @PutMapping("/suggestions/{id}")
    @ResponseBody
    public Suggestion updateSuggestion(@PathVariable int id, @RequestBody Suggestion suggestion) {
        return suggestionService.updateSuggestion(id, suggestion);
    }
}


