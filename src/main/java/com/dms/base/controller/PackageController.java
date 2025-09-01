package com.dms.base.controller;
import com.dms.base.model.Packages;
import com.dms.base.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("public/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping
    public List<Packages> getAllPackages(){
        return packageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public Optional<Packages> gatPackageById(@PathVariable long id){
        return packageService.getPackageById(id);
    }

    @PostMapping
    public Packages createPackage(@RequestBody Packages newPackage){
        return packageService.createPackage(newPackage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Packages> updatePackage(@PathVariable long id, @RequestBody Packages packageUpdated){
        try{   
            Packages updatedPackage = packageService.updatePackage(id, packageUpdated);
            return ResponseEntity.ok(updatedPackage);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
