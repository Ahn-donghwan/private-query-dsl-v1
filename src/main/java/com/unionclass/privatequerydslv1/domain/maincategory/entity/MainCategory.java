package com.unionclass.privatequerydslv1.domain.maincategory.entity;

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
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;

    @Builder
    public MainCategory(Long id, String uuid, String name) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
    }
}
