package com.dms.base.controller.generic;

import com.dms.base.controller.common.PackagesController;
import com.dms.base.dto.response.PublicPackageResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.PackagesMapper;
import com.dms.base.model.Packages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/public/packages")
public class PublicPackagesController extends PackagesController {

    @Autowired
    private PackagesMapper packageMapper;

    @GetMapping("/track/{uuid}")
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
}