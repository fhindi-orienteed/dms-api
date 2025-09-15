package com.dms.base.service;

<<<<<<<HEAD

import org.springframework.security.core.context.SecurityContextHolder;=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;>>>>>>>origin/main
import org.springframework.stereotype.Service;

import com.dms.base.config.CustomUserDetails;
import com.dms.base.model.Company;
import com.dms.base.repository.CompanyRepository;

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

    public Page<Company> getCopmpanyList(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Long getCurrentCompanyId() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return currentUser.getCompanyId();
    }

    public Company getCurrentCompany() {
        Long compnayId = getCurrentCompanyId();
        if (compnayId != null) {
            return companyRepository.findById(compnayId).orElse(null);
        }
        return null;
    }
}
