package com.kwj1270.commerce.domain.product;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.ProductType;
import com.kwj1270.commerce.domain.enums.SizeType;
import com.kwj1270.commerce.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @Column(name = "PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "PRODUCT_NAME", length = 500, nullable = false)
    private String name;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private long price;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private long quantity; // 수량

    @Column(name = "PRODUCT_DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "PRODUCT_PICTURE", length = 200, nullable = false)
    private String picture;

    @Column(name = "PRODUCT_COLOR",length = 200, nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_SIZE", nullable = false)
    private SizeType sizeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_PRODUCT_TYPE", nullable = false)
    private ProductType productType; // 카테고리

    @Builder
    public Product(String name, long price, long quantity, String description, String picture, String color, SizeType sizeType, ProductType productType){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.picture = picture;
        this.color = color;
        this.sizeType = sizeType;
        this.productType = productType;
    }


}
