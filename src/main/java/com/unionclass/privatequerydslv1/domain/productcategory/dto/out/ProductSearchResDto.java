package com.unionclass.privatequerydslv1.domain.productcategory.dto.out;

import com.unionclass.privatequerydslv1.domain.productcategory.vo.out.ProductSearchResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSearchResDto {

    private String productName;
    private Long productPrice;

    @Builder
    public ProductSearchResDto(String productName, Long productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public ProductSearchResVo toVo() {
        return ProductSearchResVo.builder()
                .productName(productName)
                .productPrice(productPrice)
                .build();
    }
}
