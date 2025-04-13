package com.unionclass.privatequerydslv1.domain.productcategory.dto.in;

import com.unionclass.privatequerydslv1.domain.productcategory.vo.in.CreateProductCategoryReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductCategoryReqDto {

    private String productName;
    private String mainCategoryName;
    private String subCategoryName;
    private String specialName;

    @Builder
    public CreateProductCategoryReqDto(String productName, String mainCategoryName, String subCategoryName, String specialName) {
        this.productName = productName;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryName = subCategoryName;
        this.specialName = specialName;
    }

    public static CreateProductCategoryReqDto from(CreateProductCategoryReqVo createProductCategoryReqVo) {
        return CreateProductCategoryReqDto.builder()
                .productName(createProductCategoryReqVo.getProductName())
                .mainCategoryName(createProductCategoryReqVo.getMainCategoryName())
                .subCategoryName(createProductCategoryReqVo.getSubCategoryName())
                .specialName(createProductCategoryReqVo.getSpecialName())
                .build();
    }
}
