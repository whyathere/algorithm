package com.zero.one;

public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        String[] arr2 = str2.split("");
        // your code
        for (int i = 0;i<str2.length();i++){
           if (str1.contains(arr2[i]) !=true){
                return false;
           }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "world";
        String[] arr = str.split("");
        for (int i =0;i<str.length();i++){
            System.out.println(arr[i]);
        }
        boolean s = str.contains(arr[1]);
        System.out.println(s);
    }
}
