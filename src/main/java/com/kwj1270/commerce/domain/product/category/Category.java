package com.kwj1270.commerce.domain.product.category;

import com.kwj1270.commerce.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name="CATEGORY")
@Entity
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 해당 카테고리 삭제되면 다 삭제함 ㅎ;
    @Column(name = "CATEGORY_ID")
    private List<Product> products = new ArrayList<>();

}
