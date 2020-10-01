package com.kwj1270.commerce.domain.user;

import com.kwj1270.commerce.domain.order.delivery.DeliveryAddress;
import com.kwj1270.commerce.domain.user.address.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="USER_ADDRESS")
@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ADDRESS")
    private Long id;

    @Column(name = "USER_ADDRESS_NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public DeliveryAddress toDeliveryAddress(){
        return DeliveryAddress.builder()
                .postCode(address.getPostCode())
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .extraAddress(address.getExtraAddress())
                .build();
    }

}
