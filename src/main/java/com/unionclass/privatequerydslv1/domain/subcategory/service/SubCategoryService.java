package com.unionclass.privatequerydslv1.domain.subcategory.service;

import com.unionclass.privatequerydslv1.domain.subcategory.dto.in.CreateSubCategoryDto;

public interface SubCategoryService {
    void createSubCategory(CreateSubCategoryDto createSubCategoryDto);

    String findUuidByName(String subCategoryName);
}
