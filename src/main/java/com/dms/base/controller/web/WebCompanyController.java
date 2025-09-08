package com.dms.base.controller.web;

import com.dms.base.dto.request.web.NewCompanyRequest;
import com.dms.base.model.Company;
import com.dms.base.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.dms.base.controller.common.CompanyController;

@RestController
@RequestMapping("/v1/web/company")
@Tag(name = "Company API", description = "Endpoints for Web Company API")
public class WebCompanyController extends CompanyController {

    private final CompanyService companyService;

    public WebCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/new")
    public ResponseEntity<Company> createCompany(@RequestBody NewCompanyRequest request) {
        Company createdCompany = companyService.createCompany(request);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentCompany() {
        Company company = companyService.getCurrentCompany();
        return ResponseEntity.ok(companyMapper.mapToWebResponse(company));
    }
}
