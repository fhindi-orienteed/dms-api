package com.dms.base.mapper;

import com.dms.base.model.Branch;
import com.dms.base.dto.response.web.WebBranchResponse;

public class BranchMapper {

    public static WebBranchResponse mapToWebResponse(Branch branch) {
        WebBranchResponse response = new WebBranchResponse();
        response.setId(branch.getId());
        response.setCompanyId(branch.getCompanyId());
        response.setName(branch.getName());
        response.setCountry(branch.getCountry());
        response.setCity(branch.getCity());
        response.setAddress(branch.getAddress());
        response.setEmail(branch.getEmail());
        response.setPhone(branch.getPhone());
        response.setMobile(branch.getMobile());
        response.setStatus(branch.getStatus());
        return response;
    }
}
