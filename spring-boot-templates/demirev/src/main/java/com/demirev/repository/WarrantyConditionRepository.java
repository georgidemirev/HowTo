package com.demirev.repository;

import com.demirev.model.WarrantyCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarrantyConditionRepository extends JpaRepository<WarrantyCondition, Long>,
        JpaSpecificationExecutor<WarrantyCondition> {

}
