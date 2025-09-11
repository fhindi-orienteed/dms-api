package com.dms.base.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dms.base.model.PackageCompany;
import com.dms.base.repository.PackageCompanyRepository;

@Service
public class PackageCompanyService {
    @Autowired
    private PackageCompanyRepository packageCompanyRepository;

    public Optional <PackageCompany>  getPackageCompanyByPackageId(long id){
        Optional <PackageCompany> existPackageCompany = packageCompanyRepository.findByPackagesId(id);
        return existPackageCompany;
    }
}
