package com.dms.base.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dms.base.model.Packages;
import com.dms.base.repository.PackagesRepository;
import com.dms.base.specification.PackageSpecification;

@Service
public class PackagesService {
    private final PackagesRepository packagesRepository;

    public PackagesService(PackagesRepository packagesRepository) {
        this.packagesRepository = packagesRepository;
    }

    public Optional<Packages> getPackageByUuid(String uuid) {
        Optional<Packages> pkg = packagesRepository.findByUuid(uuid);
        return pkg;
    }

    public Packages createNewPackage(Packages newPackages) {
        return packagesRepository.save(newPackages);
    }

    public Page<Packages> search(Pageable pageable, String customerMobile, String customerName, String uuid) {
        Specification<Packages> spec = (root, query, cb) -> cb.conjunction();

        if (customerMobile != null && !customerMobile.isBlank()) {
            spec = spec.and(PackageSpecification.hasCustomerMobile(customerMobile));
        }
        if (customerName != null && !customerName.isBlank()) {
            spec = spec.and(PackageSpecification.hasCustomerName(customerName));
        }
        if (uuid != null && !uuid.isBlank()) {
            spec = spec.and(PackageSpecification.hasPackageUUID(uuid));
        }

        return packagesRepository.findAll(spec, pageable);
    }

    public Page<Packages> getPackagesList(Pageable pageable) {
        return packagesRepository.findAll(pageable);
    }
}
