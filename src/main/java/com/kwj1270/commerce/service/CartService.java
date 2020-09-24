package com.kwj1270.commerce.service;

import com.kwj1270.commerce.domain.cart.CartRepository;
import com.kwj1270.commerce.dto.cart.CartSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Long save(CartSaveRequestDto cartSaveRequest){
        return cartRepository.save(cartSaveRequest.toEntity()).getId();
    }

}
