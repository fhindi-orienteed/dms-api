package com.dms.base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dms.base.model.Packages;

@Repository
public interface PackagesRepository extends JpaRepository<Packages, Long>, JpaSpecificationExecutor<Packages> {
    Optional<Packages> findByUuid(String uuid);

    long countByShippingStatus(String status);
}
