package com.dms.base.service;
import org.springframework.stereotype.Service;

import com.dms.base.model.PackageUpdateRequest;
import com.dms.base.repository.PackageUpdateRequestRepository;
import com.dms.base.util.Constant.Update_Request_Status;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PackageUpdateRequestService {
    @Autowired
    private PackageUpdateRequestRepository packageUpdateRequestRepository;
    
    public PackageUpdateRequest createNewRequest(long submitBy, String data,long packageId,Update_Request_Status requestStatus){
        PackageUpdateRequest request = new PackageUpdateRequest();
        request.setSubmitBy(submitBy);
        request.setPackage_id(packageId);
        request.setDataJson(data);
        request.setRequest_Status(requestStatus);
        return packageUpdateRequestRepository.save(request);
    }
}
