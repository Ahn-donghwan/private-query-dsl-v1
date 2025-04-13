package com.unionclass.privatequerydslv1.domain.special.service;

import com.unionclass.privatequerydslv1.domain.special.dto.in.CreateSpecialReqDto;

public interface SpecialService {
    void createSpecial(CreateSpecialReqDto createSpecialReqDto);

    String findUuidByName(String specialName);
}
