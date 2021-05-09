package com.demirev.repository;

import com.demirev.model.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, String>, JpaSpecificationExecutor<Warranty> {

}
