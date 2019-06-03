package com.zero.one;

public class Ninenie {
    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        for (int i=0;i<=9;i++){
            for (int j=1;j<=i;j++){
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }
    }

    public static void test02(){
        for (int i=1;i<=9;i++){
            for (int j =1;j<=9;j++){
                if(j<=i){
                    System.out.print(j+"*"+i+"="+(i*j)+"\t");
                }

            }
            System.out.println();
        }
    }
}
