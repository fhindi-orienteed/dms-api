package com.dms.base.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.PackagesController;
import com.dms.base.dto.request.mobile.MobilePackageRequest;
import com.dms.base.dto.request.mobile.MobileSearchPackageRequest;
import com.dms.base.dto.request.mobile.MobileUpdatePackageRequest;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.dto.response.mobile.MobilePackageResponse;
import com.dms.base.mapper.MobilePackageMapper;
import com.dms.base.model.Packages;
import com.dms.base.service.PackageUpdateRequestService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/mobile/packages")
@Tag(name = "Packages API", description = "These services provide APIs related to packages management for DMS Mobile Application.")
public class MobilePackagesController extends PackagesController {

    @Autowired
    private MobilePackageMapper mobilePackageMapper;
    @Autowired
    private PackageUpdateRequestService packageUpdateRequestService;

    @GetMapping("/list")
    public ResponseEntity<?> getPackagesList(@PageableDefault(size = 10) Pageable pageable) {
        Page<Packages> list = packagesService.getPackagesList(pageable);

        PaginatedResponse<MobilePackageResponse> response = new PaginatedResponse<MobilePackageResponse>();
        response.setData(packagesMapper.toMobileResponse(list.getContent()));
        response.setPage(list.getNumber());
        response.setSize(list.getSize());
        response.setTotalItems(list.getTotalElements());
        response.setTotalPages(list.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<MobilePackageResponse> createPackage(@RequestBody MobilePackageRequest request) {
        Packages newPackage = mobilePackageMapper.toPackageRequest(request);
        Packages createdPackage = packagesService.createNewPackage(newPackage);
        return ResponseEntity.ok(packagesMapper.toMobileResponse(createdPackage));
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse<MobilePackageResponse>> search(
            @RequestBody MobileSearchPackageRequest request) {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Packages> list = packagesService.search(pageable, request.getCustomerMobile(), request.getCustomerName(),
                request.getPackageUUID());

        PaginatedResponse<MobilePackageResponse> response = new PaginatedResponse<MobilePackageResponse>();
        response.setPage(list.getNumber());
        response.setSize(list.getSize());
        response.setTotalItems(list.getTotalElements());
        response.setTotalPages(list.getTotalPages());
        response.setData(packagesMapper.toMobileResponse(list.getContent()));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updatePackage(@PathVariable long id, @RequestBody MobileUpdatePackageRequest request) {
        packageUpdateRequestService.createNewRequest(id, request);
        return ResponseEntity.ok(null);
    }
}
