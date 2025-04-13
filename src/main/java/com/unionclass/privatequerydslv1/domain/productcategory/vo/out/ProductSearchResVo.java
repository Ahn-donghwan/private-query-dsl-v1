package com.unionclass.privatequerydslv1.domain.productcategory.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSearchResVo {

    private String productName;
    private Long productPrice;

    @Builder
    public ProductSearchResVo(String productName, Long productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
