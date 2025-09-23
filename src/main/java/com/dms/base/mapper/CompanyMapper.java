package com.dms.base.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.dms.base.dto.response.mobile.MobileCompanyResponse;
import com.dms.base.dto.response.web.WebCompanyResponse;
import com.dms.base.model.Company;

@Component
public class CompanyMapper {

    public WebCompanyResponse mapToWebResponse(Company company) {
        WebCompanyResponse res = new WebCompanyResponse();
        
        res.setId(company.getId());
        res.setName(company.getName());
        res.setCountry(company.getCountry());
        res.setCity(company.getCity());
        res.setAddress(company.getAddress());
        res.setEmail(company.getEmail());
        res.setPhone(company.getPhone());
        res.setMobile(company.getMobile());
        res.setFacebook(company.getFacebook());
        res.setInstegram(company.getInstegram());
        res.setRegistrationNumber(company.getRegistrationNumber());
        res.setStatus(company.getStatus());
        res.setCreatedDate(company.getCreatedDate());
        return res;
    }

    public List<WebCompanyResponse> mapToWebResponse(Page<Company> list) {
        return list.stream().map(company -> mapToWebResponse(company)).toList();
    }

    public MobileCompanyResponse mapToMobileResponse(Company company) {
        MobileCompanyResponse res = new MobileCompanyResponse();
        res.setId(company.getId());
        res.setName(company.getName());
        res.setCountry(company.getCountry());
        res.setCity(company.getCity());
        res.setAddress(company.getAddress());
        res.setEmail(company.getEmail());
        res.setPhone(company.getPhone());
        res.setMobile(company.getMobile());
        res.setFacebook(company.getFacebook());
        res.setInstegram(company.getInstegram());
        res.setRegistrationNumber(company.getRegistrationNumber());
        res.setStatus(company.getStatus());
        res.setCreatedDate(company.getCreatedDate());
        return res;
    }


}
