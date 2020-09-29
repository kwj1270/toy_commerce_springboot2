package com.kwj1270.commerce.domain.product.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="COLOR")
@Entity
public class Color {

    @Id
    @Column(name = "COLOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR_NAME", length = 20)
    private String name;

    @Column(name = "COLOR_HEX_CODE", length = 7, nullable = false)
    private String hexCode;

}
