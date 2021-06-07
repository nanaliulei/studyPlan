package com.liuliu;

import com.liuliu.adaptor.DuckAdaptor;
import com.liuliu.dynamic.pojo.Chicken;
import com.liuliu.dynamic.pojo.Duck;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:55
 * @Description
 */

public class AdaptorTest {

    public static void main(String[] args) {
        Duck duck = new Duck();
        Duck chicken = new DuckAdaptor(new Chicken());
        System.out.println("duck action:");
        duck.cock();
        duck.fly();
        System.out.println("chicken action:");
        chicken.cock();
        chicken.fly();
    }
}
