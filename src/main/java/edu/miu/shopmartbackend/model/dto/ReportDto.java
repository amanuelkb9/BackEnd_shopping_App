package edu.miu.shopmartbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.shopmartbackend.model.Report;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    @JsonProperty("report_id")
    private Long reportId;
    @JsonProperty("report_type")
    private String reportType;
    @JsonProperty("report_date")
    private LocalDate date;
    @JsonProperty("report_description")
    private String description;

//    having issues with this line ******************

    @JsonProperty("report_users")
    private List<UserDto> users;

    @JsonProperty("report_orders")
    private List<OrderDto> orders;

    public ReportDto(Long reportId, String reportType, LocalDate date, String description) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.date = date;
        this.description = description;
    }

    public static ReportDto fromReport(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setReportId(report.getReportId());
        reportDto.setReportType(report.getReportType());
        reportDto.setDate(report.getDate());
        reportDto.setDescription(report.getDescription());
        return reportDto;
    }

    public Report toReport() {
        Report report = new Report();
        report.setReportId(this.reportId);
        report.setReportType(this.reportType);
        report.setDate(this.date);
        report.setDescription(this.description);
        return report;
    }
}
