package com.kwj1270.commerce.domain.order;

public enum OrderStatus {
    READY("ready"),
    DELIVERY("delivery"),
    COMPLETE("complete");

    private String value;

    OrderStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
