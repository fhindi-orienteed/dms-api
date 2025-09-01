package com.dms.base.service;
import com.dms.base.model.Packages;
import com.dms.base.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PackagesService {
    private final PackagesRepository packagesRepository;

    @Autowired
    public PackagesService(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Optional<Packages> getPackageById(String uuid){
        return packagesRepository.findByUuid(uuid);
    }
}
