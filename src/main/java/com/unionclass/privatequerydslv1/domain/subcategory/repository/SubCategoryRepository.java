package com.unionclass.privatequerydslv1.domain.subcategory.repository;

import com.unionclass.privatequerydslv1.domain.subcategory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByName(String subCategoryName);
}
