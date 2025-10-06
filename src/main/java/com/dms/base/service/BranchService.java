package com.dms.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.model.Branch;
import com.dms.base.repository.BranchRepository;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> listByCompanyId(long companyId) {
        List<Branch> response = branchRepository.findAllByCompanyId(companyId);
        return response;
    }
}
