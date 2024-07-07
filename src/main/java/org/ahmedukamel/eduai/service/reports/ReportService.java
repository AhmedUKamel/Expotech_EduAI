package org.ahmedukamel.eduai.service.reports;

import org.ahmedukamel.eduai.model.Report;
import org.ahmedukamel.eduai.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;


    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }


    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report getReportById(int id) {
        return reportRepository.findById(id).orElse(null);
    }
    public void deleteReportById(int id) {
        reportRepository.deleteById(id);
    }


    public Report updateReport(int id, Report updatedReport) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report existingReport = optionalReport.get();
            existingReport.setUserName(updatedReport.getUserName());
            existingReport.setAvatarUrl(updatedReport.getAvatarUrl());
            existingReport.setTitle(updatedReport.getTitle());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setDate(updatedReport.getDate());
            return reportRepository.save(existingReport);
        } else {
            throw new RuntimeException("Report not found");
        }
    }


}
