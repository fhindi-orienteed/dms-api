package com.dms.base.service;

import org.springframework.stereotype.Service;

import com.dms.base.dto.request.mobile.MobileUpdatePackageRequest;
import com.dms.base.model.PackageUpdateRequest;
import com.dms.base.repository.PackageUpdateRequestRepository;
import com.dms.base.util.JsonUtility;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PackageUpdateRequestService {
    @Autowired
    private PackageUpdateRequestRepository packageUpdateRequestRepository;

    public PackageUpdateRequest createNewRequest(long packageId, MobileUpdatePackageRequest data) {
        PackageUpdateRequest request = new PackageUpdateRequest();
        request.setPackageId(packageId);
        request.setDataJson(JsonUtility.toJson(data));

        return packageUpdateRequestRepository.save(request);
    }
}
