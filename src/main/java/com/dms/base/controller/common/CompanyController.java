package com.dms.base.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.mapper.CompanyMapper;
import com.dms.base.service.CompanyService;

public abstract class CompanyController {
    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected CompanyMapper companyMapper;
}
