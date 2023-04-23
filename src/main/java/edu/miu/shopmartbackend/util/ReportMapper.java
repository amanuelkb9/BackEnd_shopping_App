package edu.miu.shopmartbackend.util;

import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    <S extends Report> ReportDto toReportDto(S report);
    <S extends Report> S toReport(ReportDto reportDto, @MappingTarget S report);
}
