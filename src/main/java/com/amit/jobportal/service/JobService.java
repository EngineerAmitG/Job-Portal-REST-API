package com.amit.jobportal.service;


import com.amit.jobportal.dto.JobDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface JobService {
    JobDto createJob(JobDto jobDto);
    Page<JobDto> getAllJobs(int page, int size, String sortBy, String direction);
    JobDto getJobById(Long id);
    List<JobDto>searchJobsByLocation(String location);
    JobDto updateJob(Long id,JobDto jobDto);
    void deleteJob(Long id);
}
