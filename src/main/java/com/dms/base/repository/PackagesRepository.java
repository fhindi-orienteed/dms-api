package com.dms.base.repository;
import com.dms.base.model.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PackagesRepository extends JpaRepository<Packages,Long>{
    Optional<Packages> findByUuid(String uuid);
}