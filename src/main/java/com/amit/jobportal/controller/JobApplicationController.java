package com.amit.jobportal.controller;

import com.amit.jobportal.dto.JobApplicationDto;
import com.amit.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping("/jobs/{jobId}/apply/{candidateId}")
    public ResponseEntity<JobApplicationDto> applyForJob(
            @PathVariable Long jobId,
            @PathVariable Long candidateId) {

        return ResponseEntity.ok(
                jobApplicationService.applyForJob(jobId, candidateId));
    }

    @GetMapping("/applications")
    public ResponseEntity<List<JobApplicationDto>> getAllApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllApplications());
    }

    @GetMapping("/applications/job/{jobId}")
    public ResponseEntity<List<JobApplicationDto>> getApplicationsByJob(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByJob(jobId));
    }

    @GetMapping("/applications/candidate/{candidateId}")
    public ResponseEntity<List<JobApplicationDto>> getApplicationsByCandidate(
            @PathVariable Long candidateId) {

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByCandidate(candidateId));
    }
}