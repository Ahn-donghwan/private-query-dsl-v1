package com.unionclass.privatequerydslv1.domain.maincategory.service;

import com.unionclass.privatequerydslv1.domain.maincategory.dto.in.CreateMainCategoryDto;
import com.unionclass.privatequerydslv1.domain.maincategory.repository.MainCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    @Transactional
    @Override
    public void createMainCategory(CreateMainCategoryDto createMainCategoryDto) {
        mainCategoryRepository.save(createMainCategoryDto.toEntity());
    }
}
