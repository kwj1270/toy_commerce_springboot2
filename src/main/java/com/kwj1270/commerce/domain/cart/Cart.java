package com.kwj1270.commerce.domain.cart;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import com.kwj1270.commerce.dto.order.OrderSaveRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "CART_SUM", nullable = false)
    private Long sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_SEQ")
    private Product product;

    @Builder
    public Cart(Long amount, Long sum, User user,  Product product){
        this.amount = amount;
        this.sum = sum;
        this.user = user;
        this.product = product;
    }

    public OrderSaveRequest toOrderSaveRequest(){
        return OrderSaveRequest.builder()
                .amount(amount)
                .sum(sum)
                .user(user)
                .product(product)
                .orderStatusType(OrderStatusType.READY)
                .build();
    }

    public void update(Long amount){
        this.amount = amount;
        this.sum = getSum();
    }

    private Long getSum() {
        return product.getPrice() * amount;
    }

}
