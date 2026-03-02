package com.amit.jobportal.serviceimpl;

import com.amit.jobportal.dto.JobDto;
import com.amit.jobportal.entity.Company;
import com.amit.jobportal.entity.Job;
import com.amit.jobportal.exception.JobException;
import com.amit.jobportal.repository.CompanyRepository;
import com.amit.jobportal.repository.JobRepository;
import com.amit.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Override
    public JobDto createJob(JobDto jobDto) {
        Company company=companyRepository.findById(jobDto.getCompanyId())
                .orElseThrow(()->new JobException("Company not found with id : "+jobDto.getCompanyId()));
        Job job=Job.builder()
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .salary(jobDto.getSalary())
                .location(jobDto.getLocation())
                .company(company)
                .build();
        Job saved=jobRepository.save(job);
        return mapToDto(saved);
    }

    @Override
    public Page<JobDto> getAllJobs(int page, int size, String sortBy, String direction) {
        Sort sort=direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return jobRepository.findAll(pageable).map(this::mapToDto);

    }

    @Override
    public JobDto getJobById(Long id) {
        Job job=jobRepository.findById(id).orElseThrow(()->
                new JobException("Job not found with id : "+id));
        return mapToDto(job);
    }

    @Override
    public List<JobDto> searchJobsByLocation(String location) {
        List<Job>jobs=jobRepository.findByLocationIgnoreCase(location);
        if(jobs.isEmpty()) throw new JobException("No job found in location : "+location);

        return jobs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public JobDto updateJob(Long id, JobDto jobDto) {
       Job job=jobRepository.findById(id).orElseThrow(()-> new JobException("Job not found with id : "+id));
       job.setTitle(jobDto.getTitle());
       job.setDescription(jobDto.getDescription());
       job.setSalary(jobDto.getSalary());
       job.setLocation(jobDto.getLocation());
       Job updated=jobRepository.save(job);
        return mapToDto(updated);
    }

    @Override
    public void deleteJob(Long id) {
        Job job=jobRepository.findById(id)
                .orElseThrow(()->new JobException("Job not found with id : "+id));
        jobRepository.delete(job);
    }

    private JobDto mapToDto(Job job){
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .salary(job.getSalary())
                .location(job.getLocation())
                .companyId(job.getCompany().getId())
                .companyName(job.getCompany().getName())
                .build();
    }
}
