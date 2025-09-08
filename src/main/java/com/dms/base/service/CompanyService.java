package com.dms.base.service;

import org.springframework.stereotype.Service;

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

    public Company getCurrentCompany() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentCompany'");
    }
}
