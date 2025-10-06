package com.dms.base.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.CompanyController;
import com.dms.base.dto.request.web.NewCompanyRequest;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.dto.response.web.WebCompanyResponse;
import com.dms.base.mapper.BranchMapper;
import com.dms.base.model.Branch;
import com.dms.base.model.Company;
import com.dms.base.service.BranchService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/company")
@Tag(name = "Company API", description = "Endpoints for Web Company API")
public class WebCompanyController extends CompanyController {
    @Autowired
    private BranchService branchService;
    @Autowired
    private BranchMapper branchMapper;
    @GetMapping("/current")
    public ResponseEntity<WebCompanyResponse> getCurrentCompany() {
        Company company = companyService.getCurrentCompany();
        return ResponseEntity.ok(companyMapper.mapToWebResponse(company));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCompanyList(@PageableDefault(size = 10) Pageable pageable) {
        Page<Company> list = companyService.getCopmpanyList(pageable);

        PaginatedResponse<WebCompanyResponse> response = new PaginatedResponse<WebCompanyResponse>();
        response.setData(companyMapper.mapToWebResponse(list));
        response.setPage(list.getNumber());
        response.setSize(list.getSize());
        response.setTotalItems(list.getTotalElements());
        response.setTotalPages(list.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<WebCompanyResponse> createNewCompany(@RequestBody NewCompanyRequest request) {
        Company company = companyService.createNewCompany(request.getName(), request.getCountry(), request.getCity(),
                request.getAddress(), request.getEmail(), request.getPhone(), request.getMobile(),
                request.getFacebook(), request.getInstegram(), request.getRegistrationNumber());

        WebCompanyResponse res = companyMapper.mapToWebResponse(company);
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("/{companyId}/branch/list")
    public ResponseEntity<?> listCompanyBranch(@PathVariable long companyId) {
        List<Branch> branchs = branchService.listByCompanyId(companyId);
        List<WebBranchResponse> response = new ArrayList<>();
        for (Branch branch : branchs) {
            response.add(branchMapper.mapToWebResponse(branch));
        }

        return ResponseEntity.ok(response);
    }
}
