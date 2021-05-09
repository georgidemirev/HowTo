package com.demirev.repository;

import com.demirev.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long>, JpaSpecificationExecutor<EndUser> {
}
