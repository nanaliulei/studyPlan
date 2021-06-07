package com.liuliu.singleton;

/**
 * @Author: liulei
 * @Time: 2021/3/5 11:19
 * @Description
 */

public class Hungry {

    private static Hungry hungry;

    static{
        System.out.println("execute static code!");
        hungry = new Hungry();
    }

    @Override
    public String toString() {
        return "Hungry{}";
    }

    private Hungry(){
        System.out.println("initialize hungry!");
    }


    public static Hungry getINstance(){
        System.out.println("get hungry!");
        return hungry;
    }


}
