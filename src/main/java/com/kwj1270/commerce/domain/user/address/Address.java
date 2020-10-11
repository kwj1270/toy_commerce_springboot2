package com.kwj1270.commerce.domain.user.address;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="ADDRESS")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "ADDRESS_POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "ADDRESS_ADDRESS", nullable = false)
    private String address;

    @Column(name = "ADDRESS_DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "ADDRESS_EXTRA_ADDRESS", nullable = false)
    private String extraAddress;

}
