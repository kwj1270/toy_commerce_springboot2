package com.kwj1270.commerce.domain.product.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="SIZE")
@Entity
public class Size {

    @Id
    @Column(name = "SIZE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SIZE_CM", nullable = false)
    private int cm;

    @Column(name = "SIZE_INCH", nullable = false)
    private int inch;



}
