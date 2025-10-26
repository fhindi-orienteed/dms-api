package com.dms.base.service;

import com.dms.base.dto.request.web.CreateNewBranchRequest;
import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.mapper.BranchMapper;
import com.dms.base.model.Branch;
import com.dms.base.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    public WebBranchResponse createBranch(CreateNewBranchRequest request) {
        Branch branch = new Branch();
        branch.setCompanyId(request.getCompanyId());
        branch.setCreatedDate(new Date());
        branch.setName(request.getName());
        branch.setCountry(request.getCountry());
        branch.setCity(request.getCity());
        branch.setAddress(request.getAddress());
        branch.setEmail(request.getEmail());
        branch.setPhone(request.getPhone());
        branch.setMobile(request.getMobile());
        branch.setStatus(request.getStatus());

        Branch savedBranch = branchRepository.save(branch);
        return branchMapper.toWebBranchResponse(savedBranch);
    }

    public List<WebBranchResponse> getBranchesByCompanyId(Long companyId) {
        List<Branch> branches = branchRepository.findAllByCompanyId(companyId);
        return branches.stream()
                .map(branchMapper::toWebBranchResponse)
                .collect(Collectors.toList());
    }
}
