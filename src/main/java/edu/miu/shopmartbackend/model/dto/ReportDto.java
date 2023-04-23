package edu.miu.shopmartbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

//    @JsonProperty("report_users")
//    private List<UserDto> users;

}
