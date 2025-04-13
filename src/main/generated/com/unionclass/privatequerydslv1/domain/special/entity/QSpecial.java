package com.unionclass.privatequerydslv1.domain.special.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpecial is a Querydsl query type for Special
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpecial extends EntityPathBase<Special> {

    private static final long serialVersionUID = -1861687622L;

    public static final QSpecial special = new QSpecial("special");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath uuid = createString("uuid");

    public QSpecial(String variable) {
        super(Special.class, forVariable(variable));
    }

    public QSpecial(Path<? extends Special> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpecial(PathMetadata metadata) {
        super(Special.class, metadata);
    }

}

