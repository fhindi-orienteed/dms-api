package com.dms.base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dms.base.model.PackageUser;

@Repository
public interface PackageUserRepository extends JpaRepository<PackageUser,Long> {
    Optional<PackageUser> findByPackagesId(Long packageId);
}
