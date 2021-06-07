package com.liuliu;

/**
 * @Author: liulei
 * @Time: 2021/3/26 15:46
 * @Description
 */

public class EnumTest {
    public static void main(String[] args) {
        String name = FldidName.ACC1.name();
        int ordinal = FldidName.ACC1.ordinal();
        System.out.println(name);
        System.out.println(ordinal);
        FldidName[] values = FldidName.values();
        for (FldidName value : values) {
            System.out.println(value.name());
            System.out.println(value.ordinal());
        }
        int flag = 1;
        switch (flag){
            case 1: break;
            case 2: break;
        }
    }
}
