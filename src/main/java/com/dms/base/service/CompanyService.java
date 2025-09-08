package com.dms.base.service;

import com.dms.base.dto.request.web.NewCompanyRequest;
import com.dms.base.model.Company;
import com.dms.base.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(NewCompanyRequest request) {
        Company company = new Company();
        
        company.setName(request.getName());
        company.setCountry(request.getCountry());
        company.setCity(request.getCity());
        company.setAddress(request.getAddress());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setMobile(request.getMobile());
        company.setFacebook(request.getFacebook());
        company.setInstegram(request.getInstegram());
        company.setRegistrationNumber(request.getRegistrationNumber());

        return companyRepository.save(company);
    }
}
