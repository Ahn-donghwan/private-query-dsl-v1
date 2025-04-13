package com.unionclass.privatequerydslv1.domain.productcategory.service;

import com.unionclass.privatequerydslv1.domain.maincategory.service.MainCategoryService;
import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.product.service.ProductService;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.in.CreateProductCategoryReqDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.in.UuidReqDto;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import com.unionclass.privatequerydslv1.domain.productcategory.repository.ProductCategoryRepository;
import com.unionclass.privatequerydslv1.domain.special.service.SpecialService;
import com.unionclass.privatequerydslv1.domain.subcategory.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductService productService;
    private final MainCategoryService mainCategoryService;
    private final SubCategoryService subCategoryService;
    private final SpecialService specialService;

    @Transactional
    @Override
    public void createProductCategory(CreateProductCategoryReqDto createProductCategoryReqDto) {
        productCategoryRepository.save(
                UuidReqDto.of(
                        productService.findUuidByName(createProductCategoryReqDto.getProductName()),
                        mainCategoryService.findUuidByName(createProductCategoryReqDto.getMainCategoryName()),
                        subCategoryService.findUuidByName(createProductCategoryReqDto.getSubCategoryName()),
                        specialService.findUuidByName(createProductCategoryReqDto.getSpecialName())
                ).toEntity()
        );
    }

    @Override
    public List<ProductSearchResDto> searchProducts(String mainCategory, String subCategory, String special, Size size, PriceRange priceRange) {
        return productService.searchProducts(mainCategory, subCategory, special, size, priceRange);
    }
}
