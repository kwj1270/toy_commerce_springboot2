package com.kwj1270.commerce.domain.order.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "DELIVERY_ADDRESS")
@Entity
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ADDRESS_ID")
    Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    Delivery delivery;

    @Column(name = "DELIVERY_ADDRESS_POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "DELIVERY_ADDRESS_ADDRESS", nullable = false)
    private String address;

    @Column(name = "DELIVERY_ADDRESS_DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "DELIVERY_ADDRESS_EXTRA_ADDRESS", nullable = false)
    private String extraAddress;

    @Builder
    public DeliveryAddress(Delivery delivery, String postCode, String address, String detailAddress, String extraAddress){
        this.delivery = delivery;
        this.postCode = postCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
    }

}
