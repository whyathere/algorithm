package com.zero;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        ThreadLocal<BigDecimal> unionDepositRandomVip = new ThreadLocal<>();
        BigDecimal bigDecimal = unionDepositRandomVip.get();
        System.out.println(bigDecimal);
    }
}
