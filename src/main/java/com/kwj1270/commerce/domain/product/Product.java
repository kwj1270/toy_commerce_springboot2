package com.kwj1270.commerce.domain.product;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.generic.money.Money;
import com.kwj1270.commerce.domain.product.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="PRODUCT")
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 500, nullable = false)
    private String name;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Money price;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private Long quantity; // 수량

    @Column(name = "PRODUCT_DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_STATUS" ,nullable = false)
    private ProductStatus productStatus;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public Product(String name, Long quantity, String description){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }


}
