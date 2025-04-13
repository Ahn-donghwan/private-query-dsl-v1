package com.unionclass.privatequerydslv1.domain.product.service;

import com.unionclass.privatequerydslv1.domain.product.dto.in.CreateProductReqDto;
import com.unionclass.privatequerydslv1.domain.product.entity.Product;
import com.unionclass.privatequerydslv1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

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
}
