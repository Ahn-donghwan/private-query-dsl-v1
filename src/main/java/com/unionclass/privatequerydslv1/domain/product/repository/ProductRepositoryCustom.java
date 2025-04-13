package com.unionclass.privatequerydslv1.domain.product.repository;

import com.unionclass.privatequerydslv1.domain.product.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductSearchResDto> searchProducts(ProductSearchParamDto productSearchParamDto);
}
