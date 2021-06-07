package com.liuliu.dynamic.pojo;

import com.liuliu.template.DrinkTemplate;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:37
 * @Description
 */

public class Coffee extends DrinkTemplate {
    @Override
    protected void addMateiral() {
        System.out.println("add coffee into water!");
    }

    @Override
    protected void putCondiment() {
        System.out.println("add milk!");
    }
}
