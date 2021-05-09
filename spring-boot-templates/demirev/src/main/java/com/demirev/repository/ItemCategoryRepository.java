package com.demirev.repository;

import com.demirev.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long>, JpaSpecificationExecutor<ItemCategory> {
    List<ItemCategory> findByParentCategoryId(Long id);
}
