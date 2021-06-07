package com.liuliu.singleton;

import java.util.HashMap;
import java.util.Map;

public enum EnumSingleton {

    INSTANCE;

    Map<Integer, Integer> map;

    private EnumSingleton(){
        init();
    }

    private void init(){
        map = new HashMap<>();
        map.put(1,2);
        System.out.println(map);
        System.out.println("init");
    }

    public String hello(){
        return "hello world!";
    }
}
