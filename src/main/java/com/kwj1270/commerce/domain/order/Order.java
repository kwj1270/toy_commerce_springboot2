package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "ORDER_TABLE")
public class Order extends BaseTimeEntity {

    @Id
    @Column(name = "ORDER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "ORDER_AMOUNT", nullable = false)
    private Long amount;

    @Column(name = "ORDER_SUM", nullable = false)
    private Long sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_SEQ")
    private Product product;


    @Column(name = "ORDER_STATUS_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusType orderStatusType;

    @Builder
    public Order(Long amount, Long sum, User user, Product product, OrderStatusType orderStatusType){
        this.amount = amount;
        this.sum = sum;
        this.user = user;
        this.product = product;
        this.orderStatusType = orderStatusType;
    }


    public void setReady(){this.orderStatusType = OrderStatusType.READY;}
    public void setDelivery(){
        this.orderStatusType = OrderStatusType.DELIVERY;
    }
    public void setComplete(){ this.orderStatusType = OrderStatusType.COMPLETE; }

    public void update(OrderStatusType orderStatusType){
        this.orderStatusType = orderStatusType;
    }

    public void update(Long amount){
        this.amount = amount;
        this.sum = getSum();
    }

    private Long getSum(){
        return product.getPrice() * amount;
    }

}
