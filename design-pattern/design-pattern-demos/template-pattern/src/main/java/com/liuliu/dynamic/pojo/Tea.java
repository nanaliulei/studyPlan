package com.liuliu.dynamic.pojo;

import com.liuliu.template.DrinkTemplate;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:36
 * @Description
 */

public class Tea extends DrinkTemplate {
    @Override
    protected void addMateiral() {
        System.out.println("add tea into water!");
    }

    @Override
    protected void putCondiment() {
        System.out.println("add lemon!");
    }
}
