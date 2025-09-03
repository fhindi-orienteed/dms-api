package com.dms.base.controller;
import com.dms.base.model.Packages;
import com.dms.base.service.PackagesService;
import com.dms.base.dto.PublicPackageResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.PackagesMapper;
import com.dms.base.mapper.MobilePackageMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dms.base.dto.MobilePackageRequest;
import com.dms.base.dto.MobilePackageResponse;

@RestController
@RequestMapping
public class PackagesController {
    @Autowired
    private PackagesService packagesService;
    
    @Autowired
    private PackagesMapper packageMapper;

    @Autowired
    private MobilePackageMapper mobilePackageMapper;

    @GetMapping("/public/packages/track/{uuid}")
    public ResponseEntity<PublicPackageResponse> getPackageByUuid(@PathVariable String uuid) {
        if (!uuid.matches("\\d{16}")) {  
            throw new BadRequestException("UUID must be exactly 16 digits.");
        } 
        Optional<Packages> pkg = packagesService.getPackageByUuid(uuid);
        if (pkg.isEmpty()) {
            throw new ObjectNotFoundException("Package with UUID " + uuid + " not found.");
        }

        PublicPackageResponse response = packageMapper.toPublicResponse(pkg.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobile/packages/new")
    public ResponseEntity<MobilePackageResponse> createPackage(@RequestBody MobilePackageRequest request){
        Packages newPackage = mobilePackageMapper.toPackageRequest(request);
        Packages createdPackage = packagesService.createnewPackage(newPackage);
        MobilePackageResponse response = new MobilePackageResponse();
        response.setId(createdPackage.getId());
        response.setMessage("Package Created Successfully");
        return ResponseEntity.ok(response);
    }


}
