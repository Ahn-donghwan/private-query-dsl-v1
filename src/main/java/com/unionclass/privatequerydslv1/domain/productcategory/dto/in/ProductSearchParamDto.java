package com.unionclass.privatequerydslv1.domain.productcategory.dto.in;

import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSearchParamDto {

    private String mainCategory;
    private String subCategory;
    private String special;
    private Size size;
    private PriceRange priceRange;

    @Builder
    public ProductSearchParamDto(String mainCategory, String subCategory, String special, Size size, PriceRange priceRange) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.special = special;
        this.size = size;
        this.priceRange = priceRange;
    }

    public static ProductSearchParamDto of(
            String mainCategory, String subCategory, String special, Size size, PriceRange priceRange
    ) {
        return ProductSearchParamDto.builder()
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .special(special)
                .size(size)
                .priceRange(priceRange)
                .build();
    }
}
