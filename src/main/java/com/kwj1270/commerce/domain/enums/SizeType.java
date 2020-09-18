package com.kwj1270.commerce.domain.enums;

public enum SizeType {
    S("s"),
    M("m"),
    L("l"),
    XL("xl"),
    XXL("xxl");

    private String size;

    SizeType(String size){ this.size = size; }
    public String getSize() { return size; }
}
