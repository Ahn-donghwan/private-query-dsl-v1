package com.unionclass.privatequerydslv1.domain.product.repository;

import com.unionclass.privatequerydslv1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    Product findByName(String productName);

}
