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
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToMany(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "seq")
    private List<Product> products;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusType orderStatusType;

    @Builder
    public Order(User user, List<Product> products, OrderStatusType orderStatusType){
        this.user = user;
        this.products = products;
        this.orderStatusType = orderStatusType;
    }

    public void setDelivery(){
        this.orderStatusType = OrderStatusType.DELIVERY;
    }

    public void setComplete(){
        this.orderStatusType = OrderStatusType.COMPLETE;
    }

    public void update(OrderStatusType orderStatusType){
        this.orderStatusType = orderStatusType;
    }

}
