package com.dms.base.controller;
import com.dms.base.model.Packages;
import com.dms.base.service.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("public/packages")
public class PackagesController {
    @Autowired
    private PackagesService packagesService;

    @GetMapping("/{uuid}")
    public Optional<Packages> gatPackageById(@PathVariable String uuid){
        return packagesService.getPackageById(uuid);
    }
}
