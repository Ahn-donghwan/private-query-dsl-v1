package com.unionclass.privatequerydslv1.domain.product.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PriceRange {

    BELOW_10K("1만원미만", 0, 9999),
    TEN_K("1만원대", 10000, 19999),
    TWENTY_K("2만원대", 20000, 29999),
    THIRTY_K("3만원대", 30000, 39999),
    FORTY_K("4만원대", 40000, 49999),
    ABOVE_50K("5만원이상", 50000, 59999)
    ;

    private final String priceRange;
    private final int min;
    private final int max;

    @JsonValue
    public String getPriceRange() {return priceRange;}

    public static PriceRange fromString(String priceRange) {
        for (PriceRange priceRangeEnum : PriceRange.values()) {
            if(priceRangeEnum.getPriceRange().equals(priceRange)) {
                return priceRangeEnum;
            }
        }
        throw new IllegalArgumentException("NO_EXIST_ENUM");
    }
}
