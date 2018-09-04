package com.zero.one;

public class YourOrder {
    public static String orders(String words){
        String[] arr = words.split(" ");
        StringBuilder result = new StringBuilder("");
        for (int i =0;i<10;i++){
            for (String s:arr){
                if (s.contains(String.valueOf(i))){
                    result.append(s + " ");
                }
            }
        }
        return result.toString().trim();
    }
}
