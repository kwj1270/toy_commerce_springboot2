package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.BaseTimeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name = "ORDER_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(User user, List<OrderLineItem> orderLineItems, OrderStatus orderStatus){
        //this.user = user;
        this.orderLineItems.addAll(orderLineItems);
        this.orderStatus = orderStatus;
    }

    public void setReady(){this.orderStatus = OrderStatus.READY;}
    public void setDelivery(){
        this.orderStatus = OrderStatus.DELIVERY;
    }
    public void setComplete(){ this.orderStatus = OrderStatus.COMPLETE; }

}
