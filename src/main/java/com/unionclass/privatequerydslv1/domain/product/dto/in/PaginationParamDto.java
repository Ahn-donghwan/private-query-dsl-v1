package com.unionclass.privatequerydslv1.domain.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class PaginationParamDto {

    private int page;
    private int size;

    @Builder
    public PaginationParamDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static PaginationParamDto of(int page, int pageSize) {
        return PaginationParamDto.builder()
                .page(page)
                .size(pageSize)
                .build();
    }

    public Pageable toPageable() {
        return PageRequest.of(page - 1, size);
    }
}
