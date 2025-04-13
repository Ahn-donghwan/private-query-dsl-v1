package com.unionclass.privatequerydslv1.domain.special.dto.in;

import com.unionclass.privatequerydslv1.domain.product.vo.in.CreateProductReqVo;
import com.unionclass.privatequerydslv1.domain.special.entity.Special;
import com.unionclass.privatequerydslv1.domain.special.vo.in.CreateSpecialReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateSpecialReqDto {

    private String specialName;

    @Builder
    public CreateSpecialReqDto(String specialName) {
        this.specialName = specialName;
    }

    public static CreateSpecialReqDto from(CreateSpecialReqVo createSpecialReqVo) {
        return CreateSpecialReqDto.builder()
                .specialName(createSpecialReqVo.getSpecialName())
                .build();
    }

    public Special toEntity() {
        return Special.builder()
                .uuid(UUID.randomUUID().toString())
                .name(specialName)
                .build();
    }
}
