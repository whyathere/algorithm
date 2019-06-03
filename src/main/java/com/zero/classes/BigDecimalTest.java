package com.zero.classes;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("2.35");
        BigDecimal s1 = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
        System.out.println(s1);
    }
}
