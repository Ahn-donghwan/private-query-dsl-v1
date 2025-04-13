package com.unionclass.privatequerydslv1.domain.productcategory.dto.in;

import com.unionclass.privatequerydslv1.domain.productcategory.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UuidReqDto {
    private String productUuid;
    private String mainCategoryUuid;
    private String subCategoryUuid;
    private String specialUuid;

    @Builder
    public UuidReqDto(String productUuid, String mainCategoryUuid, String subCategoryUuid, String specialUuid) {
        this.productUuid = productUuid;
        this.mainCategoryUuid = mainCategoryUuid;
        this.subCategoryUuid = subCategoryUuid;
        this.specialUuid = specialUuid;
    }

    public static UuidReqDto of(
            String productUuid,
            String mainCategoryUuid,
            String subCategoryUuid,
            String specialUuid
    ) {
        return UuidReqDto.builder()
                .productUuid(productUuid)
                .mainCategoryUuid(mainCategoryUuid)
                .subCategoryUuid(subCategoryUuid)
                .specialUuid(specialUuid)
                .build();
    }

    public ProductCategory toEntity() {
        return ProductCategory.builder()
                .productCategoryUuid(UUID.randomUUID().toString())
                .productUuid(productUuid)
                .mainCategoryUuid(mainCategoryUuid)
                .subCategoryUuid(subCategoryUuid)
                .specialUuid(specialUuid)
                .build();
    }
}
