package com.dms.base.service;

import org.springframework.stereotype.Service;

import com.dms.base.model.Company;
import com.dms.base.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import com.dms.base.config.CustomUserDetails;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createNewCompany(String name, String country, String city, String address, String email,
            String phone, String mobile, String facebook, String instegram, String registrationNumber) {
        Company company = new Company();

        company.setName(name);
        company.setCountry(country);
        company.setCity(city);
        company.setAddress(address);
        company.setEmail(email);
        company.setPhone(phone);
        company.setMobile(mobile);
        company.setFacebook(facebook);
        company.setInstegram(instegram);
        company.setRegistrationNumber(registrationNumber);

        return companyRepository.save(company);
    }

    public Company getCurrentCompany() {

        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        boolean isMerchant = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_MERCHANT"));

        if (!isAdmin && !isMerchant) {
            throw new AccessDeniedException("You are not allowed to access this resource");
        }

        Long companyId = currentUser.getCompanyId();

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

}
