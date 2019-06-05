package com.javabase.generic;

import java.util.Random;

       /**
        * 传入泛型实参时：
        * 定义一个生产器实现这个接口,虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
         * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
         * 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
        */
public class FruitGen implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};


    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }


           /**
            * 泛型方法的基本介绍
            *
            * @param tClass 传入的泛型实参
            * @return T 返回值为T类型
            * 说明：
            * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
            * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
            * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
            * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
            */
           public <T> T genericMethod(Class<T> tClass) throws InstantiationException,
                   IllegalAccessException {
               T instance = tClass.newInstance();
               return instance;
           }
}
