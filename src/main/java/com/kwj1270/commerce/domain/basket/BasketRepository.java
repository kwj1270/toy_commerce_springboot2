package com.kwj1270.commerce.domain.basket;

import com.kwj1270.commerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, User> {

}
