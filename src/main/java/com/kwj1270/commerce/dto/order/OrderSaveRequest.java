package com.kwj1270.commerce.dto.order;

import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.order.Order;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class OrderSaveRequest {

    private Long amount;
    private Long sum;
    private User user;
    private Product product;
    private OrderStatusType orderStatusType;

    @Builder
    public OrderSaveRequest(Long amount, Long sum, User user, Product product, OrderStatusType orderStatusType){
        this.amount = amount;
        this.sum = sum;
        this.user = user;
        this.product = product;
        this.orderStatusType = orderStatusType;
    }

    public Order toEntity(){
        return Order.builder()
                .amount(amount)
                .sum(sum)
                .user(user)
                .product(product)
                .orderStatusType(orderStatusType)
                .build();
    }

    private void update(Long amount) {
        this.amount = amount;
        this.sum = getSum();
    }

    private Long getSum(){
        return product.getPrice() * amount;
    }

}
