package edu.miu.shopmartbackend.repo;

import edu.miu.shopmartbackend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReportTypeAndDate(String reportType, LocalDate date);
}

