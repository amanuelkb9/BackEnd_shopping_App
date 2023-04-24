package edu.miu.shopmartbackend.service.impl;


import edu.miu.shopmartbackend.exception.ReportNotFoundException;
import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.repo.ReportRepository;
import edu.miu.shopmartbackend.service.ReportService;
import edu.miu.shopmartbackend.util.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;
    private ReportMapper reportMapper;


    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }
    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(() -> new ReportNotFoundException(reportId));
    }

    @Override
    public List<Report> getReportsByTypeAndDate(String reportType, LocalDate date) {
        return reportRepository.findByReportTypeAndDate(reportType, date);
    }

    public Report createReport(ReportDto reportDto) {
        Report report = new Report();
        reportMapper.toReport(reportDto, report);
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, ReportDto reportDto) {
        Report reportToUpdate = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException( id ));
        reportToUpdate.setDescription(reportDto.getDescription());
        reportToUpdate.setReportType(reportDto.getReportType());
        reportToUpdate.setDate(reportDto.getDate());
        return reportRepository.save(reportToUpdate);
    }

    @Override
    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
}
