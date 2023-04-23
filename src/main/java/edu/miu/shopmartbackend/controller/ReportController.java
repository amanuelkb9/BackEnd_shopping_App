package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.service.ReportService;
import edu.miu.shopmartbackend.util.ReportMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;
    private ReportMapper reportMapper;

    @Autowired
    public ReportController(ReportService reportService, ReportMapper reportMapper) {
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        List<ReportDto> reportDtos = reports.stream()
                .map(report -> new ReportDto(report.getReportId(), report.getReportType(), report.getDate(), report.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        ModelMapper modelMapper = new ModelMapper();
        ReportDto reportDto = modelMapper.map(report, ReportDto.class);
        return ResponseEntity.ok(reportDto);
    }


    @GetMapping(params = {"reportType", "date"})
    public ResponseEntity<List<ReportDto>> getReportsByTypeAndDate(
            @RequestParam("reportType") String reportType,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Report> reports = reportService.getReportsByTypeAndDate(reportType, date);
        List<ReportDto> reportDtos = reports.stream()
                .map(report -> new ReportDto(report.getReportId(), report.getReportType(), report.getDate(), report.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportDtos);
    }

    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDto) {
        Report savedReport = reportService.createReport(reportDto);
        ReportDto savedReportDto = reportMapper.toReportDto(savedReport);
        return ResponseEntity.ok(savedReportDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        Report updatedReport = reportService.updateReport(id, reportDto);
        ReportDto updatedReportDto = reportMapper.toReportDto(updatedReport);
        return ResponseEntity.ok(updatedReportDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
