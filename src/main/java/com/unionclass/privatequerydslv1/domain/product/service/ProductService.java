package com.unionclass.privatequerydslv1.domain.product.service;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;

import java.util.List;

public interface ProductService {

    void createProduct(CreateProductReqDto createProductReqDto);

    String findUuidByName(String productName);

    List<ProductSearchResDto> searchProducts(ProductSearchParamDto productSearchParamDto);

}
