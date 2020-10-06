package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.generic.money.domain.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderOption {
    @Column(name="ORDER_OPTION_NAME")
    private String name;

    @Column(name="ORDER_OPTION_PRICE")
    private Money price;

    @Builder
    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    OrderOption() {
    }
}
