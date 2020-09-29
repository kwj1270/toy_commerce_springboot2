package com.kwj1270.commerce.domain.generic.money;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Ratio {
    private double rate;

    public static Ratio valueOf(double rate) {
        return new Ratio(rate);
    }

    Ratio(double rate) {
        this.rate = rate;
    }

    public Money of(Money price) {
        return price.times(rate);
    }

    public double getRate() {
        return rate;
    }
}
