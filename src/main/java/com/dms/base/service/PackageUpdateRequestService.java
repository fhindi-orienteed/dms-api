package com.dms.base.service;
import org.springframework.stereotype.Service;

import com.dms.base.model.PackageUpdateRequest;
import com.dms.base.repository.PackageUpdateRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PackageUpdateRequestService {
    @Autowired
    private PackageUpdateRequestRepository packageUpdateRequestRepository;
    
    public PackageUpdateRequest createNewRequest(long packageId,String data){
        PackageUpdateRequest request = new PackageUpdateRequest();
        request.setPackageId(packageId);
        request.setDataJson(data); // Store the JSON string directly
        // Set submitBy from authentication context

        return packageUpdateRequestRepository.save(request);
    }
}
