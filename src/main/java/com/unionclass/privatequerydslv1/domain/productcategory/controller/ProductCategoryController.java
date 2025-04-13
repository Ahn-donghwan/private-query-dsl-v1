package com.unionclass.privatequerydslv1.domain.productcategory.controller;

import com.unionclass.privatequerydslv1.domain.productcategory.dto.in.CreateProductCategoryReqDto;
import com.unionclass.privatequerydslv1.domain.productcategory.service.ProductCategoryService;
import com.unionclass.privatequerydslv1.domain.productcategory.vo.in.CreateProductCategoryReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product-category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping
    public void registerProductCategory(
            @RequestBody CreateProductCategoryReqVo createProductCategoryReqVo
    ) {
        productCategoryService.createProductCategory(CreateProductCategoryReqDto.from(createProductCategoryReqVo));
    }
}
