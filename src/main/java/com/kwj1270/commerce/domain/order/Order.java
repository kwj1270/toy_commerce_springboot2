package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.BoardType;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @OneToMany(fetch = FetchType.LAZY)
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusType orderStatusType;

    @Builder
    public Order(Product product, User user, OrderStatusType orderStatusType){
        this.product = product;
        this.user = user;
        this.orderStatusType = orderStatusType;
    }

    public void update(OrderStatusType orderStatusType){
        this.orderStatusType = orderStatusType;
    }


}
