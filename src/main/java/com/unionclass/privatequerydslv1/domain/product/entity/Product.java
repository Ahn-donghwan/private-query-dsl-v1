package com.unionclass.privatequerydslv1.domain.product.entity;

import com.unionclass.privatequerydslv1.domain.product.enums.Size;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;
    private Long price;
    private Size size;

    @Builder
    public Product(Long id, String uuid, String name, Long price, Size size) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.size = size;
    }
}
