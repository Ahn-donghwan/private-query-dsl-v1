package com.unionclass.privatequerydslv1.domain.product.dto.in;

import com.unionclass.privatequerydslv1.domain.product.entity.Product;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.product.vo.in.CreateProductReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateProductReqDto {

    private String productName;
    private Long productPrice;
    private Size productSize;

    @Builder
    public CreateProductReqDto(String productName, Long productPrice, Size productSize) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
    }


    public static CreateProductReqDto from(CreateProductReqVo createProductReqVo) {
        return CreateProductReqDto.builder()
                .productName(createProductReqVo.getProductName())
                .productPrice(createProductReqVo.getProductPrice())
                .productSize(createProductReqVo.getProductSize())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .uuid(UUID.randomUUID().toString())
                .name(productName)
                .price(productPrice)
                .size(productSize)
                .build();
    }
}
