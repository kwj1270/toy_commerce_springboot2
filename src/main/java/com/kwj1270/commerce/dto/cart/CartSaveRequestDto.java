package com.kwj1270.commerce.dto.cart;


import com.kwj1270.commerce.domain.cart.Cart;
import com.kwj1270.commerce.domain.product.Product;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class CartSaveRequestDto {

    private Long amount;
    private Long sum;
    private final User user;
    private final Product product;

    @Builder
    public CartSaveRequestDto(Long amount, User user, Product product){
        this.amount = amount;
        this.sum = getSum();
        this.user = user;
        this.product = product;
    }

    public Cart toEntity(){
        return Cart.builder()
                .amount(amount)
                .sum(sum)
                .user(user)
                .product(product)
                .build();
    }

    private void update(Long amount){
        this.amount = amount;
        this.sum = getSum();
    }

    private Long getSum() {
        return product.getPrice() * amount;
    }

}
