package com.unionclass.privatequerydslv1.domain.subcategory.dto.in;

import com.unionclass.privatequerydslv1.domain.subcategory.entity.SubCategory;
import com.unionclass.privatequerydslv1.domain.subcategory.vo.in.CreateSubCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateSubCategoryDto {

    private String mainCategoryName;
    private String subCategoryName;

    @Builder
    public CreateSubCategoryDto(String mainCategoryName, String subCategoryName) {
        this.mainCategoryName = mainCategoryName;
        this.subCategoryName = subCategoryName;
    }

    public static CreateSubCategoryDto from(CreateSubCategoryVo createSubCategoryVo) {
        return CreateSubCategoryDto.builder()
                .mainCategoryName(createSubCategoryVo.getMainCategoryName())
                .subCategoryName(createSubCategoryVo.getSubCategoryName())
                .build();
    }

    public SubCategory toEntity(String mainCategoryUuid) {
        return SubCategory.builder()
                .uuid(UUID.randomUUID().toString())
                .mainCategoryUuid(mainCategoryUuid)
                .name(subCategoryName)
                .build();
    }
}
