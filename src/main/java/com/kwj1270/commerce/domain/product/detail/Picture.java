package com.kwj1270.commerce.domain.product.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="PICTURE")
@Entity
public class Picture {

    @Id
    @Column(name = "PICTURE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PICTURE_NAME", length = 20)
    private String name;

    @Column(name = "PICTURE_URL", length = 500, nullable = false)
    private String url;

}
