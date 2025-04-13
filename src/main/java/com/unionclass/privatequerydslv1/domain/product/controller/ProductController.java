package com.unionclass.privatequerydslv1.domain.product.controller;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.service.ProductService;
import com.unionclass.privatequerydslv1.domain.product.vo.in.CreateProductReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}