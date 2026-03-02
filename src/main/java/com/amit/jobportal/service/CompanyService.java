package com.amit.jobportal.service;

import com.amit.jobportal.dto.CompanyDto;
import org.springframework.data.domain.Page;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto);

    Page<CompanyDto> getAllCompanies(int page, int size, String sortBy, String direction);

    CompanyDto getCompanyById(Long id);

    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompany(Long id);
}