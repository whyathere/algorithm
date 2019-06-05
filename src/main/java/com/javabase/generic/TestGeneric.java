package com.javabase.generic;

public class TestGeneric {

    public static void main(String[] args) throws Exception {
       //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");
        System.out.println(genericInteger.getKey());
        System.out.println(genericString.getKey());


        FruitGen fruitGen = new FruitGen();
        String next = fruitGen.next();
        Object o = fruitGen.genericMethod(Class.forName("com.zero"));
        System.out.println(next);
    }

}
