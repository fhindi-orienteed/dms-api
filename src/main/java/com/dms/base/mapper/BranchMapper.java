package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.model.Branch;

@Component
public class BranchMapper {
    public WebBranchResponse mapToWebResponse(Branch branch) {
        WebBranchResponse response = new WebBranchResponse();
        response.setId(branch.getId());
        response.setAddress(branch.getAddress());
        response.setCity(branch.getCity());
        response.setCompanyId(branch.getCompanyId());
        response.setCountry(branch.getCountry());
        response.setCreatedDate(branch.getCreatedDate());
        response.setName(branch.getName());
        response.setPhone(branch.getPhone());
        response.setEmail(branch.getEmail());
        response.setStatus(branch.getStatus());
        return response;
    }
}
