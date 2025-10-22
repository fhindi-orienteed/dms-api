package com.dms.base.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dms.base.model.Branch;
import com.dms.base.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Branch createNewBranch(Long companyId, Date createdDate, String name, String country, String city,
                                  String address, String email, String phone, String mobile, String status) {
        Branch b = new Branch();

        b.setCompanyId(companyId);
        b.setCreatedDate(createdDate != null ? createdDate : new Date());
        b.setName(name);
        b.setCountry(country);
        b.setCity(city);
        b.setAddress(address);
        b.setEmail(email);
        b.setPhone(phone);
        b.setMobile(mobile);
        b.setStatus(status);

        return branchRepository.save(b);
    }

    public List<Branch> listByCompanyId(long companyId) {
        return branchRepository.findAllByCompanyId(companyId);
    }
}
