package com.amit.jobportal.serviceimpl;

import com.amit.jobportal.dto.JobApplicationDto;
import com.amit.jobportal.entity.*;
import com.amit.jobportal.exception.JobApplicationException;
import com.amit.jobportal.repository.*;
import com.amit.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public JobApplicationDto applyForJob(Long jobId, Long candidateId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobApplicationException("Job not found with id: " + jobId));

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new JobApplicationException("Candidate not found with id: " + candidateId));

        // Prevent duplicate application
        jobApplicationRepository
                .findByJobIdAndCandidateId(jobId, candidateId)
                .ifPresent(app ->
                { throw new JobApplicationException("Candidate already applied for this job"); });

        JobApplication application = JobApplication.builder()
                .job(job)
                .candidate(candidate)
                .appliedDate(LocalDate.now())
                .status("APPLIED")
                .build();

        JobApplication saved = jobApplicationRepository.save(application);

        return mapToDto(saved);
    }

    @Override
    public List<JobApplicationDto> getAllApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDto> getApplicationsByJob(Long jobId) {

        List<JobApplication> applications =
                jobApplicationRepository.findByJobId(jobId);

        return applications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDto> getApplicationsByCandidate(Long candidateId) {

        List<JobApplication> applications =
                jobApplicationRepository.findByCandidateId(candidateId);

        return applications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private JobApplicationDto mapToDto(JobApplication application) {

        return JobApplicationDto.builder()
                .id(application.getId())
                .appliedDate(application.getAppliedDate())
                .status(application.getStatus())
                .jobId(application.getJob().getId())
                .jobTitle(application.getJob().getTitle())
                .candidateId(application.getCandidate().getId())
                .candidateName(application.getCandidate().getName())
                .build();
    }
}