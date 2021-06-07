package com.liuliu.status;

import com.liuliu.machine.CandyMachine;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:15
 * @Description
 */

public class SoldOutStatus implements Status {

    private CandyMachine candyMachine;

    public SoldOutStatus(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void putCoin() {
        System.out.println("糖果已售空，不能投入硬币！");
    }

    @Override
    public void retriveCoin() {
        System.out.println("您还没有投入硬币！");
    }

    @Override
    public void pullHandle() {
        System.out.println("糖果已售空！");
    }

    @Override
    public void deliveryCandy() {
        System.out.println("没有糖果可以发放！");
    }
}
