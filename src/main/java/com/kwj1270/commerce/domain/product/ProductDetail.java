package com.kwj1270.commerce.domain.product;

import com.kwj1270.commerce.domain.product.detail.Color;
import com.kwj1270.commerce.domain.product.detail.Picture;
import com.kwj1270.commerce.domain.product.detail.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name="PRODUCT_DETAIL")
@Entity
public class ProductDetail {

    @Id
    @Column(name = "PRODUCT_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_DETAIL_ID")
    private List<Size> sizes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_DETAIL_COLOR")
    private List<Color> colors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_DETAIL_PICTURES")
    private List<Picture> pictures = new ArrayList<>();

}
