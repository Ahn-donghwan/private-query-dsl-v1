package com.unionclass.privatequerydslv1.domain.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSearchResVo {

    private String productName;
    private String productPrice;

    @Builder
    public ProductSearchResVo(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
