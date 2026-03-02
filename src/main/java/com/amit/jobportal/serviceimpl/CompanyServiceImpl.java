package com.amit.jobportal.serviceimpl;


import com.amit.jobportal.dto.CompanyDto;
import com.amit.jobportal.entity.Company;
import com.amit.jobportal.exception.CompanyException;
import com.amit.jobportal.repository.CompanyRepository;
import com.amit.jobportal.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = Company.builder()
                .name(companyDto.getName())
                .location(companyDto.getLocation())
                .build();
        Company saved=companyRepository.save(company);
        return mapToDto(saved);
    }

    @Override
    public Page<CompanyDto> getAllCompanies(int page, int size, String sortBy, String direction) {
        Sort sort=direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();

        Pageable pageable= PageRequest.of(page,size,sort);
        return companyRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company=companyRepository.findById(id)
                .orElseThrow(()->new CompanyException("Company not found with id : "+id));
        return mapToDto(company);
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company=companyRepository.findById(id)
                .orElseThrow(()->new CompanyException("Company not found with id : "+id));
        company.setName(companyDto.getName());
        company.setLocation(companyDto.getLocation());

        Company update=companyRepository.save(company);
        return mapToDto(update);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company=companyRepository.findById(id)
                .orElseThrow(()->new CompanyException("Company not found with id : "+id));
        companyRepository.delete(company);

    }

    private CompanyDto mapToDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .location(company.getLocation())
                .build();
    }
}
