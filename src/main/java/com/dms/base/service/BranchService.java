package com.dms.base.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.dms.base.model.Branch;
import com.dms.base.repository.BranchRepository;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

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
}
