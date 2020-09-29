package com.kwj1270.commerce.domain.product;

public enum ProductStatus {
    HIDE("hide"),
    SALE("sale"),
    SOLD_OUT("sold_out");

    private String value;

    ProductStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
