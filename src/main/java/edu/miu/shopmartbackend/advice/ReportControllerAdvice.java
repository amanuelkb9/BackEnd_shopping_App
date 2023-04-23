package edu.miu.shopmartbackend.advice;

import edu.miu.shopmartbackend.exception.ReportNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ReportControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ReportNotFoundException.class)
    public String reportNotFoundHandler(ReportNotFoundException ex) {
        return ex.getMessage();
    }
}