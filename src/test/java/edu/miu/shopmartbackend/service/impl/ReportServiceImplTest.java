package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.exception.ReportNotFoundException;
import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.repo.ReportRepository;
import edu.miu.shopmartbackend.util.ReportMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ReportServiceImplTest {
    /**
     * Method under test: {@link ReportServiceImpl#getAllReports()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllReports() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getAllReports(ReportServiceImpl.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getAllReports(ReportServiceImpl.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportServiceImpl()).getAllReports();
    }

    /**
     * Method under test: {@link ReportServiceImpl#getAllReports()}
     */
    @Test
    void testGetAllReports2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getAllReports(ReportServiceImpl.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportRepository reportRepository = mock(ReportRepository.class);
        ArrayList<Report> reportList = new ArrayList<>();
        when(reportRepository.findAll()).thenReturn(reportList);
        List<Report> actualAllReports = (new ReportServiceImpl(reportRepository, null)).getAllReports();
        assertSame(reportList, actualAllReports);
        assertTrue(actualAllReports.isEmpty());
        verify(reportRepository).findAll();
    }

    /**
     * Method under test: {@link ReportServiceImpl#getReportById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetReportById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getReportById(ReportServiceImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getReportById(ReportServiceImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportServiceImpl()).getReportById(1L);
    }

    /**
     * Method under test: {@link ReportServiceImpl#getReportById(Long)}
     */
    @Test
    void testGetReportById2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getReportById(ReportServiceImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = new Report();
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.of(report));
        assertSame(report, (new ReportServiceImpl(reportRepository, null)).getReportById(1L));
        verify(reportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReportServiceImpl#getReportById(Long)}
     */
    @Test
    void testGetReportById3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.getReportById(ReportServiceImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ReportNotFoundException.class,
                () -> (new ReportServiceImpl(reportRepository, null)).getReportById(1L));
        verify(reportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReportServiceImpl#createReport(ReportDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateReport() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.createReport(ReportServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.createReport(ReportServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportServiceImpl reportServiceImpl = new ReportServiceImpl();
        reportServiceImpl.createReport(new ReportDto());
    }

    /**
     * Method under test: {@link ReportServiceImpl#createReport(ReportDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateReport2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.createReport(ReportServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.createReport(ReportServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportServiceImpl reportServiceImpl = new ReportServiceImpl(mock(ReportRepository.class), null);
        reportServiceImpl.createReport(new ReportDto());
    }

    /**
     * Method under test: {@link ReportServiceImpl#createReport(ReportDto)}
     */
    @Test
    void testCreateReport3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.createReport(ReportServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = new Report();
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.save((Report) any())).thenReturn(report);

        Report report1 = new Report();
        report1.setDate(LocalDate.ofEpochDay(1L));
        report1.setDescription("The characteristics of someone or something");
        report1.setReportId(1L);
        report1.setReportType("Report Type");
        report1.setUser(new ArrayList<>());
        ReportMapper reportMapper = mock(ReportMapper.class);
        when(reportMapper.toReport((ReportDto) any(), (Report) any())).thenReturn(report1);
        ReportServiceImpl reportServiceImpl = new ReportServiceImpl(reportRepository, reportMapper);
        assertSame(report, reportServiceImpl.createReport(new ReportDto()));
        verify(reportRepository).save((Report) any());
        verify(reportMapper).toReport((ReportDto) any(), (Report) any());
    }

    /**
     * Method under test: {@link ReportServiceImpl#deleteReport(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteReport() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.deleteReport(ReportServiceImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.deleteReport(ReportServiceImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportServiceImpl()).deleteReport(1L);
    }

    /**
     * Method under test: {@link ReportServiceImpl#deleteReport(Long)}
     */
    @Test
    void testDeleteReport2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.ReportServiceImpl.deleteReport(ReportServiceImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportRepository reportRepository = mock(ReportRepository.class);
        doNothing().when(reportRepository).deleteById((Long) any());
        (new ReportServiceImpl(reportRepository, null)).deleteReport(1L);
        verify(reportRepository).deleteById((Long) any());
    }
}

