package com.unionclass.privatequerydslv1.domain.product.repository;

import com.unionclass.privatequerydslv1.domain.product.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<ProductSearchResDto> searchProducts(
            ProductSearchParamDto productSearchParamDto,
            Pageable pageable
    );
}
