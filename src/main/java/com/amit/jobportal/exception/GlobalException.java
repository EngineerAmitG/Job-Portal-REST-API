package com.amit.jobportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<Map<String,Object>>handelCompanyException(CompanyException ex){
        Map<String,Object>error=new HashMap<>();
        error.put("timestamp", LocalDate.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error","Company Error");
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JobException.class)
    public ResponseEntity<Map<String,Object>>handleJobException(JobException ex){
        Map<String,Object>error=new HashMap<>();
        error.put("timestamp",LocalDate.now());
        error.put("status",HttpStatus.NOT_FOUND.value());
        error.put("error","Job Error");
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CandidateException.class)
    public ResponseEntity<Map<String, Object>> handleCandidateException(CandidateException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Candidate Error");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JobApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleApplicationException(JobApplicationException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Application Error");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>handelGenericException(Exception ex){
        Map<String,Object>error=new HashMap<>();
        error.put("timestamp",LocalDate.now());
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error","Internal Server Error");
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
