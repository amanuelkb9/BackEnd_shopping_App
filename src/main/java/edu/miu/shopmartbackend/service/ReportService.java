package edu.miu.shopmartbackend.service;


import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.dto.ReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Report> getAllReports();
    Report getReportById(Long reportId);
    List<Report> getReportsByTypeAndDate(String reportType, LocalDate date);
    Report createReport(ReportDto report);
    Report updateReport(Long reportId, ReportDto report);
    void deleteReport(Long reportId);
}
