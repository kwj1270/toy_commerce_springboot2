package com.kwj1270.commerce.domain.basket;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.OrderStatusType;
import com.kwj1270.commerce.domain.order.Order;
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
@Entity
public class Cart extends BaseTimeEntity { // 장바구니

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Long count;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "seq")
    private List<Product> products = new ArrayList<>();

    @Builder
    public Cart(User user, Long count){
        this.user = user;
        this.count = count;
    }

    @Builder
    public Cart(User user, Long count, List<Product> products){
        this.user = user;
        this.count = count;
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Order toOrder(){
        return Order.builder()
                .user(user)
                .products(products)
                .orderStatusType(OrderStatusType.READY)
                .build();
    }

    public void update(Long Count){
        this.count = count;
    }

}
