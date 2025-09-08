package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.web.WebCompanyResponse;
import com.dms.base.model.Company;

@Component
public class CompanyMapper {

    public WebCompanyResponse mapToWebResponse(Company company) {
        WebCompanyResponse res = new WebCompanyResponse();
        res.setId(company.getId());
        // TODO: adding other properties
        return res;
    }

}
