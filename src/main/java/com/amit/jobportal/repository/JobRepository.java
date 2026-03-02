package com.amit.jobportal.repository;

import com.amit.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job>findByLocationIgnoreCase(String location);
}
