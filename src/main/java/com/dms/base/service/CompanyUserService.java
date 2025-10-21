package com.dms.base.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.model.CompanyUser;
import com.dms.base.repository.CompanyUserRepository;

@Service
public class CompanyUserService {
    @Autowired
    private CompanyUserRepository companyUserRepository;

    public CompanyUser findByUserId(long userId) {
        CompanyUser companyUser = companyUserRepository.findByUserId(userId);
        return companyUser;
    }

    public CompanyUser createNewCompanyUserRecord(long companyId, long userId, String role) {
        CompanyUser newCompanyUser = new CompanyUser();
        newCompanyUser.setCompanyId(companyId);
        newCompanyUser.setUserId(userId);
        newCompanyUser.setCreatedDate(new Date());
        newCompanyUser.setStatus(role);
        companyUserRepository.save(newCompanyUser);

        return newCompanyUser;
    }
}
