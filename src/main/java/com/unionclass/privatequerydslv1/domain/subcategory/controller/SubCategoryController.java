package com.unionclass.privatequerydslv1.domain.subcategory.controller;

import com.unionclass.privatequerydslv1.domain.subcategory.dto.in.CreateSubCategoryDto;
import com.unionclass.privatequerydslv1.domain.subcategory.service.SubCategoryService;
import com.unionclass.privatequerydslv1.domain.subcategory.vo.in.CreateSubCategoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sub-category")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public void registerSubCategory(
            @RequestBody CreateSubCategoryVo createSubCategoryVo
    ) {
        subCategoryService.createSubCategory(CreateSubCategoryDto.from(createSubCategoryVo));
    }
}
