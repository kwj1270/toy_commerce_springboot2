package com.kwj1270.commerce.domain.basket;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Basket extends BaseTimeEntity { // 장바구니

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Long count;

    @OneToMany(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Basket(User user, Long count, Product product){
        this.user = user;
        this.count = count;
        this.product = product;
    }

    public void update(Long Count){
        this.count = count;
    }

}
