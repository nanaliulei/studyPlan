package com.liuliu.singleton;

/**
 * @Author: liulei
 * @Time: 2021/3/5 12:37
 * @Description
 */

public class Lazy {

    private static Lazy lazy;

    @Override
    public String toString() {
        return "Lazy{}";
    }

    private Lazy(){
        System.out.println("initialize lazy");
    }

    public static Lazy getInstance(){
        System.out.println("get lazy!");
        if (lazy == null){
            synchronized (Lazy.class){
                if (lazy == null){
                    System.out.println("create lazy!");
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }
}
