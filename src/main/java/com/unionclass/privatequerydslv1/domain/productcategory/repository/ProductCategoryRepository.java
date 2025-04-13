package com.unionclass.privatequerydslv1.domain.productcategory.repository;

import com.unionclass.privatequerydslv1.domain.productcategory.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
