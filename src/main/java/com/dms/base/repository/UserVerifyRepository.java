package com.dms.base.repository;

import com.dms.base.model.UserVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerifyRepository extends JpaRepository<UserVerify, Long> {

}
