package com.unionclass.privatequerydslv1.domain.product.service;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;

public interface ProductService {

    void createProduct(CreateProductReqDto createProductReqDto);

    String findUuidByName(String productName);
}
