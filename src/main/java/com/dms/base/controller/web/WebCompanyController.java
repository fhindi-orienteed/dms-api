package com.dms.base.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.controller.common.CompanyController;
import com.dms.base.dto.request.web.CreateNewBranchRequest;
import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.model.Branch;
import com.dms.base.service.BranchService;
import com.dms.base.mapper.BranchMapper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/company")
@Tag(name = "Company API", description = "Endpoints for Web Company API")
public class WebCompanyController extends CompanyController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/{companyId}/branch/new")
    public ResponseEntity<WebBranchResponse> createNewBranch(
            @PathVariable Long companyId,
            @RequestBody CreateNewBranchRequest request) {

        Branch branch = branchService.createNewBranch(
                companyId,
                null,
                request.getName(),
                request.getCountry(),
                request.getCity(),
                request.getAddress(),
                request.getEmail(),
                request.getPhone(),
                request.getMobile(),
                request.getStatus()
        );

        WebBranchResponse response = BranchMapper.mapToWebResponse(branch);
        return ResponseEntity.ok(response);
    }


}
