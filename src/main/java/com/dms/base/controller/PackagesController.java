package com.dms.base.controller;
import com.dms.base.model.Packages;
import com.dms.base.service.PackagesService;
import com.dms.base.dto.PublicPackageResponse;
import com.dms.base.mapper.PackagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/packages/track")
public class PackagesController {
    @Autowired
    private PackagesService packagesService;
    
    @Autowired
    private PackagesMapper packageMapper;

    @GetMapping("/{uuid}")
    public ResponseEntity<PublicPackageResponse> gatPackageById(@PathVariable String uuid){
        Packages pkg = packagesService.getPackageById(uuid);
        PublicPackageResponse response = packageMapper.toPublicResponse(pkg);
        return ResponseEntity.ok(response);
    }
}
