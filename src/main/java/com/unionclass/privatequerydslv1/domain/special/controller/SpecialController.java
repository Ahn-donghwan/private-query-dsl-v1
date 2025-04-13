package com.unionclass.privatequerydslv1.domain.special.controller;

import com.unionclass.privatequerydslv1.domain.special.dto.in.CreateSpecialReqDto;
import com.unionclass.privatequerydslv1.domain.special.service.SpecialService;
import com.unionclass.privatequerydslv1.domain.special.vo.in.CreateSpecialReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/special")
public class SpecialController {

    private final SpecialService specialService;

    @PostMapping
    public void registerSpecial(
            @RequestBody CreateSpecialReqVo createSpecialReqVo
    ) {
        specialService.createSpecial(CreateSpecialReqDto.from(createSpecialReqVo));
    }
}
