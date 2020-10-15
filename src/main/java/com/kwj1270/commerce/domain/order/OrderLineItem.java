package com.kwj1270.commerce.domain.order;

import com.kwj1270.commerce.generic.money.domain.Money;
import com.kwj1270.commerce.domain.order.delivery.Delivery;
import com.kwj1270.commerce.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name="ORDER_LINE_ITEMS")
@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_LINE_ITEM_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 단순 참조만 하므로 CASCADE 안합니다.
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 같이 삭제
    @JoinColumn(name="ORDER_LINE_ITEM_ID")
    private Delivery delivery;

    @Column(name="PRODUCT_NAME")
    private String name;

    @Column(name="PRODUCT_COUNT")
    private int count;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ORDER_LINE_ITEM_ID")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    @Builder
    public OrderLineItem(Product product, String name, int count, List<OrderOptionGroup> groups){
        this.product = product;
        this.name = name;
        this.count = count;
        this.groups.addAll(groups);
    }

    public Money calculatePrice() {
        return Money.sum(groups, OrderOptionGroup::calculatePrice).times(count);
    }

}
