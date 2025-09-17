package com.dms.base.repository;

import com.dms.base.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{
    Company findByUserId(long userId);
}
