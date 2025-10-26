package com.dms.base.controller.web;

import com.dms.base.dto.request.web.CreateNewBranchRequest;
import com.dms.base.dto.request.web.CreateNewCompanyUserRequest;
import com.dms.base.dto.request.web.NewCompanyRequest;
import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.dto.response.web.WebCompanyUserResponse;
import com.dms.base.service.BranchService;
import com.dms.base.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/company")
public class WebCompanyController {

    private final CompanyService companyService;
    private final BranchService branchService;

    // Constructor Injection (replaces @RequiredArgsConstructor)
    public WebCompanyController(CompanyService companyService, BranchService branchService) {
        this.companyService = companyService;
        this.branchService = branchService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewCompany(@RequestBody NewCompanyRequest request) {
        return ResponseEntity.ok(companyService.createNewCompany(request));
    }

    @PostMapping("/user/create")
    public ResponseEntity<WebCompanyUserResponse> createCompanyUser(
            @RequestBody CreateNewCompanyUserRequest request) {
        WebCompanyUserResponse response = companyService.createNewCompanyUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/branch/create")
    public ResponseEntity<WebBranchResponse> createBranch(@RequestBody CreateNewBranchRequest request) {
        return ResponseEntity.ok(branchService.createBranch(request));
    }

    @GetMapping("/{companyId}/branches")
    public ResponseEntity<List<WebBranchResponse>> getCompanyBranches(@PathVariable Long companyId) {
        return ResponseEntity.ok(branchService.getBranchesByCompanyId(companyId));
    }
}
