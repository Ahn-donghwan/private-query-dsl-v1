package com.unionclass.privatequerydslv1.domain.product.vo.in;

import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import lombok.Getter;

@Getter
public class CreateProductReqVo {

    private String productName;
    private Long productPrice;
    private Size productSize;
}
