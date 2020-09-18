package com.kwj1270.commerce.domain.product;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.ProductType;
import com.kwj1270.commerce.domain.enums.SizeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private long quantity; // 수량

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 200, nullable = false)
    private String picture;

    @Column(length = 200, nullable = false)
    private String color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeType sizeType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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
