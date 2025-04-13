package com.unionclass.privatequerydslv1.domain.productcategory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCategoryUuid;
    private String productUuid;
    private String mainCategoryUuid;
    private String subCategoryUuid;
    private String specialUuid;

    @Builder
    public ProductCategory(Long id, String productCategoryUuid, String productUuid, String mainCategoryUuid, String subCategoryUuid, String specialUuid) {
        this.id = id;
        this.productCategoryUuid = productCategoryUuid;
        this.productUuid = productUuid;
        this.mainCategoryUuid = mainCategoryUuid;
        this.subCategoryUuid = subCategoryUuid;
        this.specialUuid = specialUuid;
    }
}
