package com.dms.base.service;
import org.springframework.stereotype.Service;

import com.dms.base.model.PackageUpdateRequest;
import com.dms.base.repository.PackageUpdateRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PackageUpdateRequestService {
    @Autowired
    private PackageUpdateRequestRepository packageUpdateRequestRepository;
    
    public PackageUpdateRequest createNewRequest(String data){
        PackageUpdateRequest request = new PackageUpdateRequest();
        request.setPackageId(0);
        request.setSubmitBy(0);
        request.setDataJson(data);
        request.setRequestStatus(null);
        return packageUpdateRequestRepository.save(request);
    }
}
