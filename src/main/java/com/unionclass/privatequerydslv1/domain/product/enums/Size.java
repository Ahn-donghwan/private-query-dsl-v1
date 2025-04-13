package com.unionclass.privatequerydslv1.domain.product.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Size {

    SHORT("short"),
    TALL("tall"),
    GRANDE("grande"),
    VENTI("venti"),
    TRENTA("trenta")
    ;

    private final String size;

    @JsonValue
    public String getSize() {return size;}

    @JsonCreator
    public static Size fromString(String size) {
        for(Size sizeEnum : Size.values()) {
            if(sizeEnum.getSize().equals(size)) {
                return sizeEnum;
            }
        }
        throw new IllegalArgumentException("NO_EXIST_ENUM");
    }
}
