package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.address.Address;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
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
@Entity(name = "ORDER_TABLE")
public class Order extends BaseTimeEntity {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name = "ORDER_STATUS_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusType orderStatusType;

    @Builder
    public Order(User user, List<OrderLineItem> orderLineItems, OrderStatusType orderStatusType){
        this.user = user;
        this.orderLineItems.addAll(orderLineItems);
        this.orderStatusType = orderStatusType;
    }

    public void setReady(){this.orderStatusType = OrderStatusType.READY;}
    public void setDelivery(){
        this.orderStatusType = OrderStatusType.DELIVERY;
    }
    public void setComplete(){ this.orderStatusType = OrderStatusType.COMPLETE; }

}
