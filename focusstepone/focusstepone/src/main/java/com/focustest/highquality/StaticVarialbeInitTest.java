package com.focustest.highquality;

import scala.math.Ordering;

/**
 * focus Create in 16:24 2018/12/6
 */
public class StaticVarialbeInitTest {
    public static void main(String[] args) {
        System.out.println(Price.INSTANCE.currentPrice);

        Price p = new Price(2.8);
        System.out.println(p.currentPrice);
    }
}

class Price {
    final static Price INSTANCE = new Price(2.8);
    static double initPrice = 20;
    public double currentPrice;
    public Price(double discount) {
        currentPrice = initPrice - discount;
    }
}
