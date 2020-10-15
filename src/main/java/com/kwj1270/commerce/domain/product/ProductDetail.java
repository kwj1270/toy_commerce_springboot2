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

    // ManyToMany 로 가져야 할 것 같다.
    // 중간 테이블을 만들도록 하고 단순 참조이기에 CASCADE는 제외하겠다.
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_DETAIL_ID")
    private List<Size> sizes = new ArrayList<>(); // enum으로 대체할까..

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_DETAIL_COLOR")
    private List<Color> colors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 사진은 종속적이라 삭제하는 식으로 해야겠다.
    @JoinColumn(name = "PRODUCT_DETAIL_PICTURES")
    private List<Picture> pictures = new ArrayList<>();

}
