package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.model.dto.RoleDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private ReportService reportService;
//    private ReportMapper reportMapper;

//    @Autowired
//    public ReportController(ReportService reportService) {
//        this.reportService = reportService;
//        this.reportMapper = reportMapper;
//    }


    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        List<ReportDto> reportDtos = reports.stream()
                .map(report -> {
                    ReportDto reportDto = new ReportDto(report.getReportId(), report.getReportType(), report.getDate(), report.getDescription());
                    reportDto.setUsers(report.getUser().stream()
                            .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRoles()))
                            .collect(Collectors.toList()));
                    return reportDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : report.getUser()) {
            List<RoleDto> roleDtos = new ArrayList<>();
            for (Role role : user.getRoles()) {
                RoleDto roleDto = new RoleDto();
                roleDto.setId(role.getId());
                roleDto.setRole(role.getRole());
                roleDtos.add(roleDto);
            }
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            // userDto.setRoles(role);
            userDtos.add(userDto);
        }
        ReportDto reportDto = new ReportDto();
        reportDto.setReportId(report.getReportId());
        reportDto.setReportType(report.getReportType());
        reportDto.setDate(report.getDate());
        reportDto.setDescription(report.getDescription());
        reportDto.setUsers(userDtos);
        return ResponseEntity.ok(reportDto);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
//        Report report = reportService.getReportById(id);
//        ModelMapper modelMapper = new ModelMapper();
//        ReportDto reportDto = modelMapper.map(report, ReportDto.class);
//        return ResponseEntity.ok(reportDto);
//    }


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

    //    @PostMapping
//    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDto) {
//        Report savedReport = reportService.createReport(reportDto);
//        ReportDto savedReportDto = reportMapper.toReportDto(savedReport);
//        return ResponseEntity.ok(savedReportDto);
//    }
    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDto) {
        Report savedReport = reportService.createReport(reportDto);
        ReportDto savedReportDto = new ReportDto(savedReport.getReportId(), savedReport.getReportType(), savedReport.getDate(), savedReport.getDescription());
        return ResponseEntity.ok(savedReportDto);
    }


    //    @PutMapping("/{id}")
//    public ResponseEntity<ReportDto> updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
//        Report updatedReport = reportService.updateReport(id, reportDto);
//        ReportDto updatedReportDto = reportMapper.toReportDto(updatedReport);
//        return ResponseEntity.ok(updatedReportDto);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        Report updatedReport = reportService.updateReport(id, reportDto);
        ReportDto updatedReportDto = new ReportDto(updatedReport.getReportId(), updatedReport.getReportType(), updatedReport.getDate(), updatedReport.getDescription());
        return ResponseEntity.ok(updatedReportDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
