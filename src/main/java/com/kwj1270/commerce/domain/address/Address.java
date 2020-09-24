package com.kwj1270.commerce.domain.address;

import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private List<User> users;

    @Column(name = "ADDRESS_COUNTRY")
    private String country;

    @Column(name = "ADDRESS_ZIPCODE")
    private String zipCode;

    @Column(name = "ADDRESS_MAIN")
    private String main;

    @Column(name = "ADDRESS_DETAIL")
    private String detail;


}
