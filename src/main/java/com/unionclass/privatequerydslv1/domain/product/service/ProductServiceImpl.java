package com.unionclass.privatequerydslv1.domain.product.service;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.product.repository.ProductRepository;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void createProduct(CreateProductReqDto createProductReqDto) {
        productRepository.save(createProductReqDto.toEntity());
    }

    @Override
    public String findUuidByName(String productName) {
        return productRepository.findByName(productName).getUuid();
    }

    @Override
    public List<ProductSearchResDto> searchProducts(String mainCategory, String subCategory, String special, Size size, PriceRange priceRange) {
        return productRepository.searchProducts(mainCategory, subCategory, special, size, priceRange);
    }
}
