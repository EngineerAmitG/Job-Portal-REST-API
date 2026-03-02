package com.amit.jobportal.service;


import com.amit.jobportal.dto.CandidateDto;
import org.springframework.data.domain.Page;

public interface CandidateService {
    CandidateDto createCandiate(CandidateDto candidateDto);
    Page<CandidateDto>getAllCandidates(int page,int size,String sortBy,String direction);
    CandidateDto getCandidateById(Long id);
    CandidateDto updateCandidate(Long id,CandidateDto candidateDto);
    void deleteCandidate(Long id);
}
