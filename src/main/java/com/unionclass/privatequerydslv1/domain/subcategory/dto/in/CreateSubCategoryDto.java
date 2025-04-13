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

    private String mainCategoryUuid;
    private String subCategoryName;

    @Builder
    public CreateSubCategoryDto(String mainCategoryUuid, String subCategoryName) {
        this.mainCategoryUuid = mainCategoryUuid;
        this.subCategoryName = subCategoryName;
    }

    public static CreateSubCategoryDto from(CreateSubCategoryVo createSubCategoryVo) {
        return CreateSubCategoryDto.builder()
                .mainCategoryUuid(createSubCategoryVo.getMainCategoryUuid())
                .subCategoryName(createSubCategoryVo.getSubCategoryName())
                .build();
    }

    public SubCategory toEntity() {
        return SubCategory.builder()
                .uuid(UUID.randomUUID().toString())
                .mainCategoryUuid(mainCategoryUuid)
                .name(subCategoryName)
                .build();
    }
}
