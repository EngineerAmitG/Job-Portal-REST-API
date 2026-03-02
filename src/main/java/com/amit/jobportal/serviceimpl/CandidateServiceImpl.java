package com.amit.jobportal.serviceimpl;

import com.amit.jobportal.dto.CandidateDto;
import com.amit.jobportal.entity.Candidate;
import com.amit.jobportal.exception.CandidateException;
import com.amit.jobportal.repository.CandidateRepository;
import com.amit.jobportal.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    @Override
    public CandidateDto createCandiate(CandidateDto candidateDto) {
        Candidate candidate=Candidate.builder()
                .name(candidateDto.getName())
                .email(candidateDto.getEmail())
                .skills(candidateDto.getSkills())
                .experience(candidateDto.getExperience())
                .build();
        Candidate saved=candidateRepository.save(candidate);
        return mapToDto(saved);
    }

    @Override
    public Page<CandidateDto> getAllCandidates(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(page,size,sort);

        return candidateRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(()->new CandidateException("Candidate not found with id : "+id));

        return mapToDto(candidate);
    }

    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto) {
        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(()->new CandidateException("Candidate not found with id : "+id));
        candidate.setName(candidateDto.getName());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setSkills(candidateDto.getSkills());
        candidate.setExperience(candidateDto.getExperience());

        Candidate updated=candidateRepository.save(candidate);
        return mapToDto(updated);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(()->new CandidateException("Candidate not find with id : "+id));
        candidateRepository.delete(candidate);

    }
    private CandidateDto mapToDto(Candidate candidate){
        return CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .skills(candidate.getSkills())
                .experience(candidate.getExperience())
                .build();
    }
}
