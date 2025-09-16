package com.dms.base.repository;
import org.springframework.stereotype.Repository;
import com.dms.base.model.PackageUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PackageUpdateRequestRepository extends JpaRepository<PackageUpdateRequest, Long> {
    
}
