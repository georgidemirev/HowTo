package com.demirev.repository;

import com.demirev.model.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUserRepository extends JpaRepository<BusinessUser, Long>, JpaSpecificationExecutor<BusinessUser> {
    BusinessUser findByEmail(String email);
}
