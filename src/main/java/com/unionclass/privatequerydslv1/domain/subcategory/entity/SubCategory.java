package com.unionclass.privatequerydslv1.domain.subcategory.entity;

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
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String mainCategoryUuid;
    private String name;

    @Builder
    public SubCategory(Long id, String uuid, String mainCategoryUuid, String name) {
        this.id = id;
        this.uuid = uuid;
        this.mainCategoryUuid = mainCategoryUuid;
        this.name = name;
    }
}
