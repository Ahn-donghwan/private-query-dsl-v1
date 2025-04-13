package com.unionclass.privatequerydslv1.domain.product.service;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
