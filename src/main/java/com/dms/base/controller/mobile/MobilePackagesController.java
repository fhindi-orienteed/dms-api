package com.dms.base.controller.mobile;

import com.dms.base.controller.BasePackagesController;
import com.dms.base.dto.MobilePackageRequest;
import com.dms.base.dto.MobilePackageResponse;
import com.dms.base.mapper.MobilePackageMapper;
import com.dms.base.model.Packages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile/packages")
public class MobilePackagesController extends BasePackagesController {

    @Autowired
    private MobilePackageMapper mobilePackageMapper;

    @PostMapping("/new")
    public ResponseEntity<MobilePackageResponse> createPackage(@RequestBody MobilePackageRequest request) {
        Packages newPackage = mobilePackageMapper.toPackageRequest(request);
        Packages createdPackage = packagesService.createnewPackage(newPackage);
        MobilePackageResponse response = new MobilePackageResponse();
        response.setId(createdPackage.getId());
        response.setMessage("Package Created Successfully");
        return ResponseEntity.ok(response);
    }
}