package com.dms.base.service;
import com.dms.base.model.Packages;
import com.dms.base.repository.PackagesRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PackagesService {
    private final PackagesRepository packagesRepository;

    public PackagesService(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Optional<Packages> getPackageByUuid(String uuid){
        Optional<Packages> pkg = packagesRepository.findByUuid(uuid); 
        return pkg;
    }

    public Packages createNewPackage(Packages newPackages){
        return packagesRepository.save(newPackages);
    }
}
