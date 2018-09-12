package com.zero.one;

public class DRoot {

    public static int digital_root(int n) {
        while(n > 9){
            n = n/10 + n % 10;
        }
        return(n);
    }
}
