package com.unionclass.privatequerydslv1.domain.product.repository;

import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductSearchResDto> searchProducts(String mainCategory, String subCategory, String special, Size size, PriceRange priceRange);
}
