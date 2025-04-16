package com.unionclass.privatequerydslv1.domain.product.controller;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.dto.in.PaginationParamDto;
import com.unionclass.privatequerydslv1.domain.product.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.product.service.ProductService;
import com.unionclass.privatequerydslv1.domain.product.vo.in.CreateProductReqVo;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import com.unionclass.privatequerydslv1.domain.productcategory.vo.out.ProductSearchResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void registerProduct(
            @RequestBody CreateProductReqVo createProductReqVo
    ) {
        productService.createProduct(CreateProductReqDto.from(createProductReqVo));
    }

    @GetMapping("/searches/pagination")
    public Page<ProductSearchResVo> searchProducts(
            @RequestParam(required = false) String mainCategory,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) String special,
            @RequestParam(required = false) Size size,
            @RequestParam(required = false) PriceRange priceRange,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        return productService.searchProducts(
                ProductSearchParamDto.of(mainCategory, subCategory, special, size, priceRange),
                PaginationParamDto.of(page, pageSize)
        ).map(ProductSearchResDto::toVo);
    }
}