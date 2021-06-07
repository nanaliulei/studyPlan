package com.liuliu.adaptor;

import com.liuliu.dynamic.pojo.Chicken;
import com.liuliu.dynamic.pojo.Duck;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:47
 * @Description
 */

public class DuckAdaptor extends Duck {

    private Chicken chicken;

    public DuckAdaptor(Chicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public void cock() {
        chicken.cock();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++){
            chicken.fly();
        }
    }
}
