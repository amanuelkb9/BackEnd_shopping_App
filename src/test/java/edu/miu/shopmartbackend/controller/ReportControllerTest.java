package edu.miu.shopmartbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.Report;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.repo.ReportRepository;
import edu.miu.shopmartbackend.service.impl.ReportServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReportController.class})
@ExtendWith(SpringExtension.class)
class ReportControllerTest {
    @Autowired
    private ReportController reportController;

    /**
     * Method under test: {@link ReportController#getAllReports()}
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
        //       at edu.miu.shopmartbackend.controller.ReportController.getAllReports(ReportController.java:38)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getAllReports(ReportController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportController()).getAllReports();
    }

    /**
     * Method under test: {@link ReportController#getAllReports()}
     */
    @Test
    void testGetAllReports2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getAllReports(ReportController.java:38)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportRepository reportRepository = mock(ReportRepository.class);
        ArrayList<Report> reportList = new ArrayList<>();
        when(reportRepository.findAll()).thenReturn(reportList);
        ResponseEntity<List<ReportDto>> actualAllReports = (new ReportController(
                new ReportServiceImpl(reportRepository, null))).getAllReports();
        assertEquals(reportList, actualAllReports.getBody());
        assertEquals(HttpStatus.OK, actualAllReports.getStatusCode());
        assertTrue(actualAllReports.getHeaders().isEmpty());
        verify(reportRepository).findAll();
    }

    /**
     * Method under test: {@link ReportController#getAllReports()}
     */
    @Test
    void testGetAllReports3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getAllReports(ReportController.java:38)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = new Report();
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());

        ArrayList<Report> reportList = new ArrayList<>();
        reportList.add(report);
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findAll()).thenReturn(reportList);
        ResponseEntity<List<ReportDto>> actualAllReports = (new ReportController(
                new ReportServiceImpl(reportRepository, null))).getAllReports();
        assertTrue(actualAllReports.hasBody());
        assertTrue(actualAllReports.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAllReports.getStatusCode());
        ReportDto getResult = actualAllReports.getBody().get(0);
        assertEquals("1970-01-02", getResult.getDate().toString());
        assertEquals("Report Type", getResult.getReportType());
        assertEquals(1L, getResult.getReportId().longValue());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        verify(reportRepository).findAll();
    }

    /**
     * Method under test: {@link ReportController#getAllReports()}
     */
    @Test
    void testGetAllReports4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getAllReports(ReportController.java:38)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = new Report();
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());

        Report report1 = new Report();
        report1.setDate(LocalDate.ofEpochDay(1L));
        report1.setDescription("Description");
        report1.setReportId(2L);
        report1.setReportType("42");
        report1.setUser(new ArrayList<>());

        ArrayList<Report> reportList = new ArrayList<>();
        reportList.add(report1);
        reportList.add(report);
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findAll()).thenReturn(reportList);
        ResponseEntity<List<ReportDto>> actualAllReports = (new ReportController(
                new ReportServiceImpl(reportRepository, null))).getAllReports();
        assertTrue(actualAllReports.hasBody());
        assertTrue(actualAllReports.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAllReports.getStatusCode());
        List<ReportDto> body = actualAllReports.getBody();
        ReportDto getResult = body.get(1);
        assertEquals("Report Type", getResult.getReportType());
        ReportDto getResult1 = body.get(0);
        assertEquals("42", getResult1.getReportType());
        assertEquals(2L, getResult1.getReportId().longValue());
        assertEquals("Description", getResult1.getDescription());
        assertEquals("1970-01-02", getResult1.getDate().toString());
        assertEquals(1L, getResult.getReportId().longValue());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("1970-01-02", getResult.getDate().toString());
        verify(reportRepository).findAll();
    }

    /**
     * Method under test: {@link ReportController#getReportById(Long)}
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
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportController()).getReportById(1L);
    }

    /**
     * Method under test: {@link ReportController#getReportById(Long)}
     */
    @Test
    void testGetReportById2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = new Report();
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.of(report));
        ResponseEntity<ReportDto> actualReportById = (new ReportController(new ReportServiceImpl(reportRepository, null)))
                .getReportById(1L);
        assertTrue(actualReportById.hasBody());
        assertTrue(actualReportById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualReportById.getStatusCode());
        ReportDto body = actualReportById.getBody();
        assertEquals("1970-01-02", body.getDate().toString());
        assertEquals("Report Type", body.getReportType());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals(1L, body.getReportId().longValue());
        verify(reportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReportController#getReportById(Long)}
     */
    @Test
    void testGetReportById3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        LocalDate date = LocalDate.ofEpochDay(1L);

        Report report = new Report(1L, "Report Type", date, "The characteristics of someone or something",
                new ArrayList<>());
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.of(report));
        ResponseEntity<ReportDto> actualReportById = (new ReportController(new ReportServiceImpl(reportRepository, null)))
                .getReportById(1L);
        assertTrue(actualReportById.hasBody());
        assertTrue(actualReportById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualReportById.getStatusCode());
        ReportDto body = actualReportById.getBody();
        assertEquals("1970-01-02", body.getDate().toString());
        assertEquals("Report Type", body.getReportType());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals(1L, body.getReportId().longValue());
        verify(reportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReportController#getReportById(Long)}
     */
    @Test
    void testGetReportById4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = mock(Report.class);
        when(report.getReportId()).thenReturn(1L);
        when(report.getDescription()).thenReturn("The characteristics of someone or something");
        when(report.getReportType()).thenReturn("Report Type");
        when(report.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(report).setDate((LocalDate) any());
        doNothing().when(report).setDescription((String) any());
        doNothing().when(report).setReportId((Long) any());
        doNothing().when(report).setReportType((String) any());
        doNothing().when(report).setUser((List<User>) any());
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.of(report));
        ResponseEntity<ReportDto> actualReportById = (new ReportController(new ReportServiceImpl(reportRepository, null)))
                .getReportById(1L);
        assertTrue(actualReportById.hasBody());
        assertTrue(actualReportById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualReportById.getStatusCode());
        ReportDto body = actualReportById.getBody();
        assertEquals("1970-01-02", body.getDate().toString());
        assertEquals("Report Type", body.getReportType());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals(1L, body.getReportId().longValue());
        verify(reportRepository).findById((Long) any());
        verify(report).getReportId();
        verify(report).getDescription();
        verify(report).getReportType();
        verify(report).getDate();
        verify(report).setDate((LocalDate) any());
        verify(report).setDescription((String) any());
        verify(report).setReportId((Long) any());
        verify(report).setReportType((String) any());
        verify(report).setUser((List<User>) any());
    }

    /**
     * Method under test: {@link ReportController#getReportById(Long)}
     */
    @Test
    void testGetReportById5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.getReportById(ReportController.java:48)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        Report report = mock(Report.class);
        when(report.getReportId()).thenReturn(1L);
        when(report.getDescription()).thenReturn("The characteristics of someone or something");
        when(report.getReportType()).thenReturn("Report Type");
        when(report.getDate()).thenReturn(null);
        doNothing().when(report).setDate((LocalDate) any());
        doNothing().when(report).setDescription((String) any());
        doNothing().when(report).setReportId((Long) any());
        doNothing().when(report).setReportType((String) any());
        doNothing().when(report).setUser((List<User>) any());
        report.setDate(LocalDate.ofEpochDay(1L));
        report.setDescription("The characteristics of someone or something");
        report.setReportId(1L);
        report.setReportType("Report Type");
        report.setUser(new ArrayList<>());
        ReportRepository reportRepository = mock(ReportRepository.class);
        when(reportRepository.findById((Long) any())).thenReturn(Optional.of(report));
        ResponseEntity<ReportDto> actualReportById = (new ReportController(new ReportServiceImpl(reportRepository, null)))
                .getReportById(1L);
        assertTrue(actualReportById.hasBody());
        assertTrue(actualReportById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualReportById.getStatusCode());
        ReportDto body = actualReportById.getBody();
        assertNull(body.getDate());
        assertEquals("Report Type", body.getReportType());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals(1L, body.getReportId().longValue());
        verify(reportRepository).findById((Long) any());
        verify(report).getReportId();
        verify(report).getDescription();
        verify(report).getReportType();
        verify(report).getDate();
        verify(report).setDate((LocalDate) any());
        verify(report).setDescription((String) any());
        verify(report).setReportId((Long) any());
        verify(report).setReportType((String) any());
        verify(report).setUser((List<User>) any());
    }

    /**
     * Method under test: {@link ReportController#createReport(ReportDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateReport() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: edu.miu.shopmartbackend.model.dto.ReportDto["report_date"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/reports")
                .contentType(MediaType.APPLICATION_JSON);
        LocalDate date = LocalDate.ofEpochDay(1L);

        ReportDto reportDto = new ReportDto();
        reportDto.setDate(date);
        reportDto.setDescription("The characteristics of someone or something");
        reportDto.setReportId(1L);
        reportDto.setReportType("Report Type");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(reportDto));
        MockMvcBuilders.standaloneSetup(reportController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ReportController#deleteReport(Long)}
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
        //       at edu.miu.shopmartbackend.controller.ReportController.deleteReport(ReportController.java:81)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:687)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.deleteReport(ReportController.java:81)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReportController()).deleteReport(1L);
    }

    /**
     * Method under test: {@link ReportController#deleteReport(Long)}
     */
    @Test
    void testDeleteReport2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.controller.ReportController.deleteReport(ReportController.java:81)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:687)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        ReportRepository reportRepository = mock(ReportRepository.class);
        doNothing().when(reportRepository).deleteById((Long) any());
        ResponseEntity<Void> actualDeleteReportResult = (new ReportController(
                new ReportServiceImpl(reportRepository, null))).deleteReport(1L);
        assertNull(actualDeleteReportResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteReportResult.getStatusCode());
        assertTrue(actualDeleteReportResult.getHeaders().isEmpty());
        verify(reportRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ReportController#getReportsByTypeAndDate(String, LocalDate)}
     */
    @Test
    void testGetReportsByTypeAndDate() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/reports");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("date", String.valueOf((Object) null))
                .param("reportType", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reportController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ReportController#updateReport(Long, ReportDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateReport() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: edu.miu.shopmartbackend.model.dto.ReportDto["report_date"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/reports/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        LocalDate date = LocalDate.ofEpochDay(1L);

        ReportDto reportDto = new ReportDto();
        reportDto.setDate(date);
        reportDto.setDescription("The characteristics of someone or something");
        reportDto.setReportId(1L);
        reportDto.setReportType("Report Type");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(reportDto));
        MockMvcBuilders.standaloneSetup(reportController).build().perform(requestBuilder);
    }
}

