package com.dms.base.service;
import com.dms.base.model.Packages;
import com.dms.base.repository.PackagesRepository;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackagesService {
    private final PackagesRepository packagesRepository;

    @Autowired
    public PackagesService(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Packages getPackageById(String uuid){
        if(!isValidUuid(uuid)){
            throw new BadRequestException("Invalid UUID format. UUID must be 36 characters long.");
        }

        Optional<Packages> pkg = packagesRepository.findByUuid(uuid);
        if(pkg.isEmpty()){
            throw new ObjectNotFoundException("Package with UUID " + uuid + " not found.");
        }
        
        return pkg.get();
    }

    private boolean isValidUuid(String uuid) {
        if (uuid == null || uuid.length() != 36) {
            return false;
        }
        
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
