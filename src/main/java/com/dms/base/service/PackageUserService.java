package com.dms.base.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dms.base.model.PackageUser;
import com.dms.base.repository.PackageUserRepository;

@Service
public class PackageUserService {
    @Autowired
    private PackageUserRepository packageUserRepository;

    public Optional<PackageUser> getPackageUserByPackageId(long id){
        Optional <PackageUser> existPackageUser = packageUserRepository.findByPackagesId(id);
        return existPackageUser;
    }
}
