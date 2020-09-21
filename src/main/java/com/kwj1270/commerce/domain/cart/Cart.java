package com.kwj1270.commerce.domain.cart;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.order.Order;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity { // 장바구니

    @Id
    @Column(name = "CART_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "CART_AMOUNT", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_SEQ")
    private Product product;

    @Builder
    public Cart(User user, Long amount){
        this.user = user;
        this.amount = amount;
    }

    @Builder
    public Cart(User user, Long amount, Product product){
        this.user = user;
        this.amount = amount;
        this.product = product;
    }

    public Order toOrder(){
        return Order.builder()
                .user(user)
                .product(product)
                .orderStatusType(OrderStatusType.READY)
                .build();
    }

    public void update(Long Count){
        this.amount = amount;
    }

}
