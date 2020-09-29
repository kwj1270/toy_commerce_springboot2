package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.generic.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
