package com.dms.base.service;

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
}
