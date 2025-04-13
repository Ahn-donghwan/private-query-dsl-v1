package com.unionclass.privatequerydslv1.domain.productcategory.controller;

import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.in.CreateProductCategoryReqDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import com.unionclass.privatequerydslv1.domain.productcategory.service.ProductCategoryService;
import com.unionclass.privatequerydslv1.domain.productcategory.vo.in.CreateProductCategoryReqVo;
import com.unionclass.privatequerydslv1.domain.productcategory.vo.out.ProductSearchResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/searches")
    public List<ProductSearchResVo> searchProducts(
            @RequestParam(required = false) String mainCategory,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) String special,
            @RequestParam(required = false) Size size,
            @RequestParam(required = false) PriceRange priceRange
    ) {
        return productCategoryService.searchProducts(
                mainCategory, subCategory, special, size, priceRange
        ).stream().map(ProductSearchResDto::toVo).toList();
    }
}
