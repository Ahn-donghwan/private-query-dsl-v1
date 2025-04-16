package com.unionclass.privatequerydslv1.domain.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unionclass.privatequerydslv1.domain.maincategory.entity.QMainCategory;
import com.unionclass.privatequerydslv1.domain.product.dto.in.PaginationParamDto;
import com.unionclass.privatequerydslv1.domain.product.dto.in.ProductSearchParamDto;
import com.unionclass.privatequerydslv1.domain.product.entity.QProduct;
import com.unionclass.privatequerydslv1.domain.product.enums.PriceRange;
import com.unionclass.privatequerydslv1.domain.product.enums.Size;
import com.unionclass.privatequerydslv1.domain.productcategory.dto.out.ProductSearchResDto;
import com.unionclass.privatequerydslv1.domain.productcategory.entity.QProductCategory;
import com.unionclass.privatequerydslv1.domain.special.entity.QSpecial;
import com.unionclass.privatequerydslv1.domain.subcategory.entity.QSubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QProductCategory productCategoryQ = QProductCategory.productCategory;
    QProduct productQ = QProduct.product;
    QMainCategory mainCategoryQ = QMainCategory.mainCategory;
    QSubCategory subCategoryQ = QSubCategory.subCategory;
    QSpecial specialQ = QSpecial.special;

    @Override
    public Page<ProductSearchResDto> searchProducts(
            ProductSearchParamDto productSearchParamDto,
            Pageable pageable
    ) {

        List<ProductSearchResDto> content = queryFactory
                .select(Projections.constructor(
                        ProductSearchResDto.class,
                        productQ.name,
                        productQ.price
                ))
                .from(productQ)
                .join(productCategoryQ).on(productQ.uuid.eq(productCategoryQ.productUuid))
                .leftJoin(mainCategoryQ).on(productCategoryQ.mainCategoryUuid.eq(mainCategoryQ.uuid))
                .leftJoin(subCategoryQ).on(productCategoryQ.subCategoryUuid.eq(subCategoryQ.uuid))
                .leftJoin(specialQ).on(productCategoryQ.specialUuid.eq(specialQ.uuid))
                .where(
                        mainCategoryEquals(productSearchParamDto.getMainCategory(), mainCategoryQ),
                        subCategoryEquals(productSearchParamDto.getSubCategory(), subCategoryQ),
                        specialEquals(productSearchParamDto.getSpecial(), specialQ),
                        sizeEquals(productSearchParamDto.getSize(), productQ),
                        priceRangeEquals(productSearchParamDto.getPriceRange(), productQ)
                )
                .orderBy(productQ.price.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(
                queryFactory
                        .select(productQ.count())
                        .from(productQ)
                        .join(productCategoryQ).on(productQ.uuid.eq(productCategoryQ.productUuid))
                        .leftJoin(mainCategoryQ).on(productCategoryQ.mainCategoryUuid.eq(mainCategoryQ.uuid))
                        .leftJoin(subCategoryQ).on(productCategoryQ.subCategoryUuid.eq(subCategoryQ.uuid))
                        .leftJoin(specialQ).on(productCategoryQ.specialUuid.eq(specialQ.uuid))
                        .where(
                                mainCategoryEquals(productSearchParamDto.getMainCategory(), mainCategoryQ),
                                subCategoryEquals(productSearchParamDto.getSubCategory(), subCategoryQ),
                                specialEquals(productSearchParamDto.getSpecial(), specialQ),
                                sizeEquals(productSearchParamDto.getSize(), productQ),
                                priceRangeEquals(productSearchParamDto.getPriceRange(), productQ)
                        )
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression priceRangeEquals(PriceRange priceRange, QProduct productQ) {
        return priceRange != null ? productQ.price.between(priceRange.getMin(), priceRange.getMax()) : null;
    }


    private BooleanExpression sizeEquals(Size size, QProduct productQ) {
        return size != null ? productQ.size.eq(size) : null;
    }

    private BooleanExpression specialEquals(String special, QSpecial specialQ) {
        return special != null ? specialQ.name.eq(special) : null;
    }


    private BooleanExpression subCategoryEquals(String subCategory, QSubCategory subCategoryQ) {
        return subCategory != null ? subCategoryQ.name.eq(subCategory) : null;
    }

    private BooleanExpression mainCategoryEquals(String mainCategory, QMainCategory mainCategoryQ) {
        return mainCategory != null ? mainCategoryQ.name.eq(mainCategory) : null;
    }
}
