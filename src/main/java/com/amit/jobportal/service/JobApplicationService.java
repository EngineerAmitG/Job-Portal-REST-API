package com.amit.jobportal.service;

import com.amit.jobportal.dto.JobApplicationDto;

import java.util.List;

public interface JobApplicationService {

    JobApplicationDto applyForJob(Long jobId, Long candidateId);

    List<JobApplicationDto> getAllApplications();

    List<JobApplicationDto> getApplicationsByJob(Long jobId);

    List<JobApplicationDto> getApplicationsByCandidate(Long candidateId);
}