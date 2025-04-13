package com.unionclass.privatequerydslv1.domain.subcategory.service;

import com.unionclass.privatequerydslv1.domain.maincategory.entity.MainCategory;
import com.unionclass.privatequerydslv1.domain.maincategory.service.MainCategoryService;
import com.unionclass.privatequerydslv1.domain.subcategory.dto.in.CreateSubCategoryDto;
import com.unionclass.privatequerydslv1.domain.subcategory.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryService mainCategoryService;

    @Transactional
    @Override
    public void createSubCategory(CreateSubCategoryDto createSubCategoryDto) {
        subCategoryRepository.save(createSubCategoryDto.toEntity(
                mainCategoryService.findUuidByName(createSubCategoryDto.getMainCategoryName())));
    }

    @Override
    public String findUuidByName(String subCategoryName) {
        return subCategoryRepository.findByName(subCategoryName).getUuid();
    }
}
