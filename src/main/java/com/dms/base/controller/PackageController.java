package com.dms.base.controller;
import com.dms.base.model.Package;
import com.dms.base.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping
    public List<Package> getAllPackages(){
        return packageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public Optional<Package> gatPackageById(@PathVariable long id){
        return packageService.getPackageById(id);
    }

    @PostMapping
    public Package createPackage(@RequestBody Package newPackage){
        return packageService.createPackage(newPackage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable long id, @RequestBody Package packageUpdated){
        try{   
            Package updatedPackage = packageService.updatePackage(id, packageUpdated);
            return ResponseEntity.ok(updatedPackage);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable long id){
        packageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }
}
