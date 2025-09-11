package com.dms.base.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dms.base.model.PackageCompany;

@Repository
public interface PackageCompanyRepository extends JpaRepository<PackageCompany,Long> {
    Optional <PackageCompany> findByPackagesId(Long packageId);
}
