package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.domain.generic.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class OrderOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name="ORDER_OPTIONS", joinColumns = @JoinColumn(name="ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> orderOptions;

    @Builder
    public OrderOptionGroup(String name, List<OrderOption> orderOptions){
        this.name = name;
        this.orderOptions.addAll(orderOptions);
    }

    public Money calculatePrice() {
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

}
