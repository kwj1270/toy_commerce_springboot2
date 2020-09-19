package com.kwj1270.commerce.domain.enums;

public enum OrderStatusType {
    READY("ready"),
    DELIVERY("delivery"),
    COMPLETE("complete");

    private String value;

    OrderStatusType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
