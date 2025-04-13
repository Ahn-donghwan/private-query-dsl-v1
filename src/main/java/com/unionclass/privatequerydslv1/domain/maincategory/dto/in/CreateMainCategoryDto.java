package com.unionclass.privatequerydslv1.domain.maincategory.dto.in;

import com.unionclass.privatequerydslv1.domain.maincategory.entity.MainCategory;
import com.unionclass.privatequerydslv1.domain.maincategory.vo.in.CreateMainCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateMainCategoryDto {

    private String mainCategoryName;

    @Builder
    public CreateMainCategoryDto(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public static CreateMainCategoryDto from(CreateMainCategoryVo createMainCategoryVo) {
        return CreateMainCategoryDto.builder()
                .mainCategoryName(createMainCategoryVo.getMainCategoryName())
                .build();
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .uuid(UUID.randomUUID().toString())
                .name(mainCategoryName)
                .build();
    }
}
