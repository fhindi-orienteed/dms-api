package com.dms.base.service;

import org.springframework.stereotype.Service;

import com.dms.base.model.Company;
import com.dms.base.model.User;
import com.dms.base.repository.CompanyRepository;
import com.dms.base.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository , UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
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

        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUser.getUsername();

        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        boolean isMerchant = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_MERCHANT"));

        if (!isAdmin && !isMerchant) {
            throw new AccessDeniedException("You are not allowed to access this resource");
        }
        
        
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        return companyRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

    }
}
