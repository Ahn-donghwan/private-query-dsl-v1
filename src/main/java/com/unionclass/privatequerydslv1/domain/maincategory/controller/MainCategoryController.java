package com.unionclass.privatequerydslv1.domain.maincategory.controller;

import com.unionclass.privatequerydslv1.domain.maincategory.dto.in.CreateMainCategoryDto;
import com.unionclass.privatequerydslv1.domain.maincategory.service.MainCategoryService;
import com.unionclass.privatequerydslv1.domain.maincategory.vo.in.CreateMainCategoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main-category")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @PostMapping
    public void registerMainCategory(
            @RequestBody CreateMainCategoryVo createMainCategoryVo
    ) {
        mainCategoryService.createMainCategory(CreateMainCategoryDto.from(createMainCategoryVo));
    }
}
