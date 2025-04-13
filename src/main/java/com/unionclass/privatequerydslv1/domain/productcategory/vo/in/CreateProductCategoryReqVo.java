package com.unionclass.privatequerydslv1.domain.productcategory.vo.in;

import lombok.Getter;

@Getter
public class CreateProductCategoryReqVo {

    private String productName;
    private String mainCategoryName;
    private String subCategoryName;
    private String specialName;
}
