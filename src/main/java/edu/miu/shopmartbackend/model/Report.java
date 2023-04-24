package edu.miu.shopmartbackend.model;


import edu.miu.shopmartbackend.model.dto.ReportDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long reportId;
    private String reportType;
    private LocalDate date;
    private String description;
    @OneToMany
    private List<User> user;

    @OneToMany
    private List<Order> orders;

    public static Report fromReportDto(ReportDto reportDto) {
        Report report = new Report();
        report.setReportId(reportDto.getReportId());
        report.setReportType(reportDto.getReportType());
        report.setDate(reportDto.getDate());
        report.setDescription(reportDto.getDescription());

//        List<User> users = new ArrayList<>();
//        for (UserDto userDto : reportDto.getUsers()) {
//            User user = new User();
//            user.setId(userDto.getId());
//            user.setUsername(userDto.getUsername());
//            user.setPassword(userDto.getPassword());
//            user.setRoles(userDto.getRoles());
//            users.add(user);
//        }
//        report.setUser(users);
        return report;
    }

    public ReportDto toReportDto() {
        ReportDto reportDto = new ReportDto();
        reportDto.setReportId(this.reportId);
        reportDto.setReportType(this.reportType);
        reportDto.setDate(this.date);
        reportDto.setDescription(this.description);
        return reportDto;
    }

}
