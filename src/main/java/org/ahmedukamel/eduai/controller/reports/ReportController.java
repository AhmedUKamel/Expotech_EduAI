package org.ahmedukamel.eduai.controller.reports;

import org.ahmedukamel.eduai.model.Report;
import org.ahmedukamel.eduai.service.reports.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) {

        return  ResponseEntity.ok(reportService.getReportById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable int id, @RequestBody Report reportDetails) {
        Report updatedReport = reportService.updateReport(id, reportDetails);
        return ResponseEntity.ok(updatedReport);
    }
    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable int id) {
        reportService.deleteReportById(id);
    }

}
