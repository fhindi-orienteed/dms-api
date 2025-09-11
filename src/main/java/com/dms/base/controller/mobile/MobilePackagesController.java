package com.dms.base.controller.mobile;

import com.dms.base.controller.common.PackagesController;
import com.dms.base.dto.request.mobile.MobilePackageRequest;
import com.dms.base.dto.response.mobile.MobilePackageResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.MobilePackageMapper;
import com.dms.base.model.PackageCompany;
import com.dms.base.model.PackageUser;
import com.dms.base.model.Packages;
import com.dms.base.service.PackageCompanyService;
import com.dms.base.service.PackageUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mobile/packages")
@Tag(name = "Packages API", description = "These services provide APIs related to packages management for DMS Mobile Application.")
public class MobilePackagesController extends PackagesController {
    @Autowired
    private MobilePackageMapper mobilePackageMapper;

    @Autowired
    private PackageUserService packageUserService;

    @Autowired
    private PackageCompanyService packageCompanyService;


    @PostMapping("/new")
    public ResponseEntity<MobilePackageResponse> createPackage(@RequestBody MobilePackageRequest request) {
        Packages newPackage = mobilePackageMapper.toPackageRequest(request);
        Packages createdPackage = packagesService.createNewPackage(newPackage);
        MobilePackageResponse response = new MobilePackageResponse();
        response.setId(createdPackage.getId());
        response.setMessage("Package Created Successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MobilePackageResponse> updatePackage(@PathVariable long id, @RequestBody MobilePackageRequest request) {
        Optional<Packages> pkg = packagesService.getPackageById(id);
        if (pkg.isEmpty()) {
            throw new ObjectNotFoundException("Package with id " + id + " not found.");
        }

        Optional<PackageUser> existPackageUser = packageUserService.getPackageUserByPackageId(id);
        if (existPackageUser.isEmpty()) {
            throw new BadRequestException("Package with id " + id + " not at merchant side.");
        }

        Optional<PackageCompany> existPackageCompany = packageCompanyService.getPackageCompanyByPackageId(id);
        if (existPackageCompany.isPresent()) {
            throw new BadRequestException("Package with id " + id + " not available (delivered)");
        }

        Packages mappedPackage = mobilePackageMapper.toPackageRequest(request);
        Packages updatedPackage = packagesService.updatePackage(id, mappedPackage);
        MobilePackageResponse response = mobilePackageMapper.toPublicResponse(updatedPackage);
        response.setId(updatedPackage.getId());
        response.setMessage("Package Created Successfully");
        return ResponseEntity.ok(response);
    }
}
